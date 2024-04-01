package me.imsonmia.epqapi.controller;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.micrometer.common.lang.Nullable;
import lombok.Data;
import lombok.Setter;
import me.imsonmia.epqapi.model.User;
import me.imsonmia.epqapi.repository.UserRepository;
@Controller
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * AuthData
     */
    @Data
    public class AuthData {
        private boolean success;
        private boolean hasProfile;
        private boolean exists;
        private String authMessage;
        private long authResponseTimestampMillis;

        public AuthData(boolean success, boolean hasProfile, boolean exists, @Nullable String authMessage) {
            this.success = success;
            this.hasProfile = hasProfile;
            this.exists = exists;
            this.authMessage = authMessage == null ? "" : authMessage;
            this.authResponseTimestampMillis = new Date().getTime();
        }
    }

    public static class AuthRequestData {
        private String userName;
        private String userPasswordHash;
        public String getUserName() {
            return userName;
        }
        public String getUserPasswordHash() {
            return userPasswordHash;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public void setUserPasswordHash(String userPasswordHash) {
            this.userPasswordHash = userPasswordHash;
        }
        public AuthRequestData() {
            
        }
        public AuthRequestData(String userName, String userPasswordHash) {
            this.userName = userName;
            this.userPasswordHash = userPasswordHash;
        }
    }
    @Data
    public static class RegisterRequestData {
        @Setter
        private String userName;
        @Setter
        private String newUserPassword;
        public RegisterRequestData() {}
        public RegisterRequestData(String userName, String newUserPassword) {
            this.userName = userName;
            this.newUserPassword = newUserPassword;
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthData> authRegister(@RequestBody RegisterRequestData registerRequestData) {
        if (userRepository.existsByUserName(registerRequestData.userName)) {
            return new ResponseEntity<>(new AuthData(false, true, true, "Login failed. Username already exists."), null,
                    200);
        }
        User newUser = new User(UUID.randomUUID().toString(), registerRequestData.userName, new Date(), new Date(),
                registerRequestData.newUserPassword);
        userRepository.save(newUser);
        return new ResponseEntity<AuthData>(new AuthData(true, true, true, "Login successful. Used registration endpoint."),
                null, 200);
    }

    /**
     * Authentication HTTPS endpoint used instead of client-side verification which
     * is unsafe
     */
    @PostMapping(value = "/auth",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthData> authLogin(@RequestBody AuthRequestData authRequestData) {
        if (!userRepository.existsByUserName(authRequestData.userName)) {

            logger.info("Invalid login since user doesn't exist");
            return new ResponseEntity<AuthData>(new AuthData(
                    false,
                    false,
                    false,
                    "Login invalid: User doesn't exist in database."),
                    null,
                    200);
        }
        String pwdHash = userRepository.findByUserName(authRequestData.userName).get().getPasswordHash();
        // NOTE Cannot use straight == comparison. Must use equals function @ 22:17 2024-04-01
        if (!pwdHash.equals(authRequestData.getUserPasswordHash())) {
            return new ResponseEntity<>(new AuthData(
                false,
                true,
                true,
                "Login invalid: Password incorrect."
                ), null, 200);
        } else {
            return new ResponseEntity<>(new AuthData(true, true, true, "Authentication success"), null, 200);
        }
    }
}
