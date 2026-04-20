package org.woridge.settings_management.dto;

import org.woridge.settings_management.enumeration.ErrorCode;

import java.time.Instant;

public record ErrorResponse<T>(
        Instant timestamp,
        T message,
        ErrorCode errorCode
) {

    public static <T> ErrorResponse<T> createErrorResponse(T message, ErrorCode errorCode) {
        return new ErrorResponse<>(Instant.now(), message, errorCode);
    }
}
