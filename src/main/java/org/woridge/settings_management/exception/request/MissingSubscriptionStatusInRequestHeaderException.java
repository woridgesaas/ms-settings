package org.woridge.settings_management.exception.request;

public class MissingSubscriptionStatusInRequestHeaderException extends RuntimeException {

    public MissingSubscriptionStatusInRequestHeaderException(String message) {

        super(message);
    }
}
