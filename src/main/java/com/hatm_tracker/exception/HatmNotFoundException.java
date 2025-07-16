package com.hatm_tracker.exception;

public class HatmNotFoundException extends RuntimeException {
    public HatmNotFoundException(String message) {
        super(message);
    }
}
