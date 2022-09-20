package com.example.serviceauthformicroproject04.exception;

/**
 * Created by Vladislav Domaniewski
 */

public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}
