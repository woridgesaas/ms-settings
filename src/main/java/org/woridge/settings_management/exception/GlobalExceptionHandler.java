package org.woridge.settings_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.woridge.settings_management.dto.ErrorResponse;
import org.woridge.settings_management.enumeration.ErrorCode;
import org.woridge.settings_management.exception.annual_leave.AnnualBalanceNotFoundException;
import org.woridge.settings_management.exception.exceptional_leave.ExceptionalLeaveNotFoundException;
import org.woridge.settings_management.exception.exceptional_leave.GrantedDaysLessThanLegalBalanceException;
import org.woridge.settings_management.exception.request.MissingCinInRequestHeaderException;
import org.woridge.settings_management.exception.request.MissingRoleInRequestHeaderException;
import org.woridge.settings_management.exception.request.MissingSubdomainInRequestHeaderException;
import org.woridge.settings_management.exception.request.MissingSubscriptionStatusInRequestHeaderException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Annual leave exceptions
    @ExceptionHandler(AnnualBalanceNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleAnnualBalanceNotFoundException(AnnualBalanceNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.ANNUAL_BALANCE_NOT_FOUND
                        )
                );
    }

    // Exceptional leave exceptions
    @ExceptionHandler(ExceptionalLeaveNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleExceptionalLeaveNotFoundException(ExceptionalLeaveNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.EXCEPTIONAL_LEAVE_NOT_FOUND
                        )
                );
    }

    @ExceptionHandler(GrantedDaysLessThanLegalBalanceException.class)
    public ResponseEntity<ErrorResponse<String>> handleGrantedDaysLessThanLegalBalanceException(GrantedDaysLessThanLegalBalanceException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.GRANTED_DAYS_LESS_THAN_LEGAL_BALANCE
                        )
                );
    }

    // Request exceptions
    @ExceptionHandler(MissingCinInRequestHeaderException.class)
    public ResponseEntity<ErrorResponse<String>> handleMissingCinInRequestHeaderException(MissingCinInRequestHeaderException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MISSING_GATEWAY_FORWARDED_HEADER
                        )
                );
    }

    @ExceptionHandler(MissingRoleInRequestHeaderException.class)
    public ResponseEntity<ErrorResponse<String>> handleMissingRoleInRequestHeaderException(MissingRoleInRequestHeaderException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MISSING_GATEWAY_FORWARDED_HEADER
                        )
                );
    }

    @ExceptionHandler(MissingSubdomainInRequestHeaderException.class)
    public ResponseEntity<ErrorResponse<String>> handleMissingSubdomainInRequestHeaderException(MissingSubdomainInRequestHeaderException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MISSING_GATEWAY_FORWARDED_HEADER
                        )
                );
    }

    @ExceptionHandler(MissingSubscriptionStatusInRequestHeaderException.class)
    public ResponseEntity<ErrorResponse<String>> handleMissingSubscriptionStatusInRequestHeaderException(MissingSubscriptionStatusInRequestHeaderException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MISSING_GATEWAY_FORWARDED_HEADER
                        )
                );
    }

    // System exceptions
    // Request validation exception (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        List<String> errorList = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(
                        ErrorResponse.createErrorResponse(
                                errorList,
                                ErrorCode.REQUEST_VALIDATION_FAILED
                        )
                );
    }

    // Enum deserialization exception
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(
                        ErrorResponse.createErrorResponse(
                                "Invalid enum value",
                                ErrorCode.INVALID_ENUM_VALUE
                        )
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse<String>> handleIllegalArgumentException(IllegalArgumentException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.INVALID_ARGUMENT
                        )
                );
    }
}
