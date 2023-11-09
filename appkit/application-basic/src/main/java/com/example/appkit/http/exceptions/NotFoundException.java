package com.example.appkit.http.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String errorCode;

    public NotFoundException(String message, String errorCode) {
        super(message);

        this.errorCode = errorCode;
    }
    public NotFoundException(String message) {
        this(message, null);
    }

    public NotFoundException() {
        this(null);
    }
}