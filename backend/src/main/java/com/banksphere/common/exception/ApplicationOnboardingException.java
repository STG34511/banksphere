package com.banksphere.common.exception;

import org.aspectj.bridge.IMessage;

public class ApplicationOnboardingException extends RuntimeException{
    public ApplicationOnboardingException(String message) {
        super(message);
    }
}
