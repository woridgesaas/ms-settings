package org.woridge.settings_management.exception.work_schedule;

public class ExitTimeNotAfterEntryTimeException extends RuntimeException {

    public ExitTimeNotAfterEntryTimeException(String message) {

        super(message);
    }
}
