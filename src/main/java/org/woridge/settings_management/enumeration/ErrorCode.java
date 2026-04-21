package org.woridge.settings_management.enumeration;

public enum ErrorCode {
    // Annual balance
    ANNUAL_BALANCE_NOT_FOUND,

    // Exceptional leave
    EXCEPTIONAL_LEAVE_NOT_FOUND,
    GRANTED_DAYS_LESS_THAN_LEGAL_BALANCE,

    // Gateway
    MISSING_GATEWAY_FORWARDED_HEADER,
    REQUEST_NOT_ROUTED_THROUGH_GATEWAY,

    // Request validation (@Valid)
    REQUEST_VALIDATION_FAILED,

    // Illegal argument
    INVALID_ARGUMENT,

    // Enumeration value
    INVALID_ENUM_VALUE,
}
