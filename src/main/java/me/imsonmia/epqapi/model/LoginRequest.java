package me.imsonmia.epqapi.model;

import lombok.Getter;
import lombok.Setter;

public class LoginRequest {
    @Getter
    @Setter
    private String loginUserId;
    @Getter
    @Setter
    private String passwdHashInput;
}
