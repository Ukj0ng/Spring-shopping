package com.uk.shopping.user.handler.exception;

import com.uk.shopping.common.constants.ErrorMessage;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}