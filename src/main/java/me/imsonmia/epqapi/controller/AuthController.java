package me.imsonmia.epqapi.controller;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.micrometer.common.lang.Nullable;
import me.imsonmia.epqapi.repository.UserRepository;

@RequestMapping("/api/v1")
public class AuthController {
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * AuthData
     */
    public class AuthData {

        private boolean success;
        private boolean hasProfile;
        private boolean exists;
        private String authMessage;
        private long authResponseTimestampMillis;

        public AuthData(boolean success, boolean hasProfile, @Nullable String authMessage) {
            this.success = success;
            this.hasProfile = hasProfile;
            this.authMessage = authMessage == null ? "" : authMessage;
            this.authResponseTimestampMillis = new Date().getTime();
        }
    }

    public abstract class AuthRequestData {
        private String userName;
        private String userPasswordHash;

        public AuthRequestData(String userName, String userPasswordHash) {
            this.userName = userName;
            this.userPasswordHash = userPasswordHash;
        }
    }

    /**
     * Authentication HTTPS endpoint used instead of client-side verification which
     * is unsafe
     */
    @PostMapping("/auth")
    public ResponseEntity<AuthData> authLogin(@RequestBody AuthRequestData authRequestData) {
        if (!userRepository.existsByUserName(authRequestData.userName)) {

            logger.info("Invalid login since user doesn't exist");
            return new ResponseEntity<AuthData>(new AuthData(
                    false,
                    false,
                    "Login invalid: User doesn't exist in database."),
                    null,
                    200);
        }
        String pwdHash = userRepository.findByUserName(authRequestData.userName).get().getPasswordHash();
        if (pwdHash != authRequestData.userPasswordHash) {
            return new ResponseEntity<>(new AuthData(false, true, "Login invalid: Password incorrect."), null, 200);
        } else {
            return new ResponseEntity<>(new AuthData(true, true, "Authentication success"), null, 200);
        }
    }
}
