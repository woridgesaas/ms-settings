package org.woridge.settings_management.exception.request;

public class MissingRoleInRequestHeaderException extends RuntimeException {

    public MissingRoleInRequestHeaderException(String message) {

        super(message);
    }
}
