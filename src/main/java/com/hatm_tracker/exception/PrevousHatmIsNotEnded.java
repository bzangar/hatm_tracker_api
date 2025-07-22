package com.hatm_tracker.exception;

public class PrevousHatmIsNotEnded extends RuntimeException {
    public PrevousHatmIsNotEnded(String message) {
        super(message);
    }
}
