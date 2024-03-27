package com.todoApp.todoApp.Security.SecurityExceptions;

import java.io.IOException;

public class UsernameAlreadyExistsException extends IOException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
