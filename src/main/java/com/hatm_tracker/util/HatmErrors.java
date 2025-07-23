package com.hatm_tracker.util;

public enum HatmErrors {
    HATM_NOT_FOUND("Hatm does not exists!!"),
    PREVOUS_HATM_IS_NOT_ENDED("Prevous Hatm Is Not Ended"),
    USER_NOT_FOUND("User doesn't exists!!!"),
    READING_PROGRESS_NOT_FOUND("Reading progress does not exists"),
    NULL_ID("Id must not be null");


    private final String message;

    public String getMessage(){
        return this.message;
    }

    HatmErrors(String message){
        this.message = message;
    }
}
