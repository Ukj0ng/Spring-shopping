package com.uk.shopping.common.constants;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    INVALID_USERNAME_OR_PASSWORD("Invalid username or password"),
    USER_NOT_FOUND("User not found"),
    INVALID_USER("Invalid user");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}


