package org.woridge.settings_management.exception.exceptional_leave;

public class GrantedDaysLessThanLegalBalanceException extends RuntimeException {

    public GrantedDaysLessThanLegalBalanceException(String message) {

        super(message);
    }
}
