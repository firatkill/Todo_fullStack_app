package com.todoApp.todoApp.Security.SecurityExceptions;

import java.io.IOException;

public class InvalidTokenException extends IOException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
