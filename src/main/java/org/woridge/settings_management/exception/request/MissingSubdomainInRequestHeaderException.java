package org.woridge.settings_management.exception.request;

public class MissingSubdomainInRequestHeaderException extends RuntimeException {

    public MissingSubdomainInRequestHeaderException(String message) {

        super(message);
    }
}
