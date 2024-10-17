package com.example.event_management.exceptions;

public class TokenExpiredException extends  RuntimeException {
    public TokenExpiredException(String message) {
        super(message);
    }
}
