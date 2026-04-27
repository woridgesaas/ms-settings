package org.woridge.settings_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.woridge.settings_management.dto.ErrorResponse;
import org.woridge.settings_management.enumeration.ErrorCode;
import org.woridge.settings_management.exception.absence_justification.AbsenceJustificationNotConfiguredException;
import org.woridge.settings_management.exception.alert_threshold.AlertThresholdNotConfiguredException;
import org.woridge.settings_management.exception.alert_threshold.CriticalThresholdLessThanWarningThresholdException;
import org.woridge.settings_management.exception.annual_leave.AnnualBalanceNotConfigureException;
import org.woridge.settings_management.exception.document_template.*;
import org.woridge.settings_management.exception.exceptional_leave.ExceptionalLeaveNotConfiguredException;
import org.woridge.settings_management.exception.exceptional_leave.GrantedDaysLessThanLegalBalanceException;
import org.woridge.settings_management.exception.file.FileConversionFailedException;
import org.woridge.settings_management.exception.file.MissingFileExtensionException;
import org.woridge.settings_management.exception.minio.*;
import org.woridge.settings_management.exception.recruitment_site.*;
import org.woridge.settings_management.exception.request.MissingCinInRequestHeaderException;
import org.woridge.settings_management.exception.request.MissingRoleInRequestHeaderException;
import org.woridge.settings_management.exception.request.MissingSubdomainInRequestHeaderException;
import org.woridge.settings_management.exception.request.MissingSubscriptionStatusInRequestHeaderException;
import org.woridge.settings_management.exception.work_schedule.*;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Annual leave exceptions
    @ExceptionHandler(AnnualBalanceNotConfigureException.class)
    public ResponseEntity<ErrorResponse<String>> handleAnnualBalanceNotFoundException(AnnualBalanceNotConfigureException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.ANNUAL_BALANCE_NOT_CONFIGURED
                        )
                );
    }

    // Exceptional leave exceptions
    @ExceptionHandler(ExceptionalLeaveNotConfiguredException.class)
    public ResponseEntity<ErrorResponse<String>> handleExceptionalLeaveNotFoundException(ExceptionalLeaveNotConfiguredException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.EXCEPTIONAL_LEAVE_NOT_CONFIGURED
                        )
                );
    }

    @ExceptionHandler(GrantedDaysLessThanLegalBalanceException.class)
    public ResponseEntity<ErrorResponse<String>> handleGrantedDaysLessThanLegalBalanceException(GrantedDaysLessThanLegalBalanceException exception) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.GRANTED_DAYS_LESS_THAN_LEGAL_BALANCE
                        )
                );
    }

    // Absence justification exceptions
    @ExceptionHandler(AbsenceJustificationNotConfiguredException.class)
    public ResponseEntity<ErrorResponse<String>> handleAbsenceJustificationNotFoundException(AbsenceJustificationNotConfiguredException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.ABSENCE_JUSTIFICATION_NOT_CONFIGURED
                        )
                );
    }

    // Alert threshold exceptions
    @ExceptionHandler(AlertThresholdNotConfiguredException.class)
    public ResponseEntity<ErrorResponse<String>> handleAlertThresholdNotFoundException(AlertThresholdNotConfiguredException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.ALERT_THRESHOLD_NOT_CONFIGURED
                        )
                );
    }

    @ExceptionHandler(CriticalThresholdLessThanWarningThresholdException.class)
    public ResponseEntity<ErrorResponse<String>> handleCriticalThresholdLessThanWarningThresholdException(CriticalThresholdLessThanWarningThresholdException exception) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.CRITICAL_THRESHOLD_LESS_THAN_WARNING_THRESHOLD
                        )
                );
    }

    // Work schedule exceptions
    @ExceptionHandler(DailyHoursNotAllowedForFixedScheduleException.class)
    public ResponseEntity<ErrorResponse<String>> handleDailyHoursNotAllowedForFixedScheduleException(DailyHoursNotAllowedForFixedScheduleException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.DAILY_HOURS_NOT_ALLOWED_FOR_FIXED_SCHEDULE
                        )
                );
    }

    @ExceptionHandler(DailyHoursOutOfAllowedRangeException.class)
    public ResponseEntity<ErrorResponse<String>> handleDailyHoursOutOfAllowedRangeException(DailyHoursOutOfAllowedRangeException exception) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.DAILY_HOURS_OUT_OF_ALLOWED_RANGE
                        )
                );
    }

    @ExceptionHandler(EntryOrExitTimeNotAllowedForFlexibleScheduleException.class)
    public ResponseEntity<ErrorResponse<String>> handleEntryOrExitTimeNotAllowedForFlexibleScheduleException(EntryOrExitTimeNotAllowedForFlexibleScheduleException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.ENTRY_OR_EXIT_TIME_NOT_ALLOWED_FOR_FLEXIBLE_SCHEDULE
                        )
                );
    }

    @ExceptionHandler(ExitTimeNotAfterEntryTimeException.class)
    public ResponseEntity<ErrorResponse<String>> handleExitTimeNotAfterEntryTimeException(ExitTimeNotAfterEntryTimeException exception) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.EXIT_TIME_NOT_AFTER_ENTRY_TIME
                        )
                );
    }

    @ExceptionHandler(MissingDailyHoursForFlexibleScheduleException.class)
    public ResponseEntity<ErrorResponse<String>> handleMissingDailyHoursForFlexibleScheduleException(MissingDailyHoursForFlexibleScheduleException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MISSING_DAILY_HOURS_FOR_FLEXIBLE_SCHEDULE
                        )
                );
    }

    @ExceptionHandler(MissingEntryTimeForFixedScheduleException.class)
    public ResponseEntity<ErrorResponse<String>> handleMissingEntryTimeForFixedScheduleException(MissingEntryTimeForFixedScheduleException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MISSING_ENTRY_TIME_FOR_FIXED_SCHEDULE
                        )
                );
    }

    @ExceptionHandler(MissingExitTimeForFixedScheduleException.class)
    public ResponseEntity<ErrorResponse<String>> handleMissingExitTimeForFixedScheduleException(MissingExitTimeForFixedScheduleException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MISSING_EXIT_TIME_FOR_FIXED_SCHEDULE
                        )
                );
    }

    @ExceptionHandler(WorkScheduleNotConfiguredException.class)
    public ResponseEntity<ErrorResponse<String>> handleWorkScheduleNotConfiguredException(WorkScheduleNotConfiguredException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.WORK_SCHEDULE_NOT_CONFIGURED
                        )
                );
    }

    // Document template exceptions
    @ExceptionHandler(DocumentTemplateNotConfiguredException.class)
    public ResponseEntity<ErrorResponse<String>> handleDocumentTemplateNotConfiguredException(DocumentTemplateNotConfiguredException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.DOCUMENT_TEMPLATE_NOT_CONFIGURED
                        )
                );
    }

    @ExceptionHandler(InvalidDocumentTemplateContentTypeException.class)
    public ResponseEntity<ErrorResponse<String>> handleInvalidDocumentTemplateContentTypeException(InvalidDocumentTemplateContentTypeException exception) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.INVALID_DOCUMENT_TEMPLATE_CONTENT_TYPE
                        )
                );
    }

    @ExceptionHandler(InvalidDocumentTemplateExtensionException.class)
    public ResponseEntity<ErrorResponse<String>> handleInvalidDocumentTemplateExtensionException(InvalidDocumentTemplateExtensionException exception) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.INVALID_DOCUMENT_TEMPLATE_EXTENSION
                        )
                );
    }

    @ExceptionHandler(InvalidDocumentTemplatePathException.class)
    public ResponseEntity<ErrorResponse<String>> handleInvalidDocumentTemplatePathException(InvalidDocumentTemplatePathException exception) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.INVALID_DOCUMENT_TEMPLATE_PATH
                        )
                );
    }

    @ExceptionHandler(InvalidDocumentTemplateSizeException.class)
    public ResponseEntity<ErrorResponse<String>> handleInvalidDocumentTemplateSizeException(InvalidDocumentTemplateSizeException exception) {

        return ResponseEntity
                .status(HttpStatus.CONTENT_TOO_LARGE)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.INVALID_DOCUMENT_TEMPLATE_SIZE
                        )
                );
    }

    // Minio exceptions
    @ExceptionHandler(MinioBucketCheckFailedException.class)
    public ResponseEntity<ErrorResponse<String>> handleMinioBucketCheckFailedException(MinioBucketCheckFailedException exception) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MINIO_BUCKET_CHECK_FAILED
                        )
                );
    }

    @ExceptionHandler(MinioBucketCreationFailedException.class)
    public ResponseEntity<ErrorResponse<String>> handleMinioBucketCreationFailedException(MinioBucketCreationFailedException exception) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MINIO_BUCKET_CREATION_FAILED
                        )
                );
    }

    @ExceptionHandler(MinioDeleteFileFailedException.class)
    public ResponseEntity<ErrorResponse<String>> handleMinioDeleteFileFailedException(MinioDeleteFileFailedException exception) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MINIO_DELETE_FILE_FAILED
                        )
                );
    }

    @ExceptionHandler(MinioDownloadFileFailedException.class)
    public ResponseEntity<ErrorResponse<String>> handleMinioDownloadFileFailedException(MinioDownloadFileFailedException exception) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MINIO_DOWNLOAD_FILE_FAILED
                        )
                );
    }

    @ExceptionHandler(MinioPresignedUrlGenerationFailedException.class)
    public ResponseEntity<ErrorResponse<String>> handleMinioPresignedUrlGenerationFailedException(MinioPresignedUrlGenerationFailedException exception) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MINIO_PRESIGNED_URL_GENERATION_FAILED
                        )
                );
    }

    @ExceptionHandler(MinioUploadFileFailedException.class)
    public ResponseEntity<ErrorResponse<String>> handleMinioUploadFileFailedException(MinioUploadFileFailedException exception) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MINIO_UPLOAD_FILE_FAILED
                        )
                );
    }

    // File exceptions
    @ExceptionHandler(FileConversionFailedException.class)
    public ResponseEntity<ErrorResponse<String>> handleFileConversionFailedException(FileConversionFailedException exception) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.FILE_CONVERSION_FAILED
                        )
                );
    }

    @ExceptionHandler(MissingFileExtensionException.class)
    public ResponseEntity<ErrorResponse<String>> handleMissingFileExtensionException(MissingFileExtensionException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MISSING_FILE_EXTENSION
                        )
                );
    }

    // Recruitment site exceptions
    @ExceptionHandler(InvalidRecruitmentSiteLogoContentTypeException.class)
    public ResponseEntity<ErrorResponse<String>> handleInvalidRecruitmentSiteLogoContentTypeException(InvalidRecruitmentSiteLogoContentTypeException exception) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.INVALID_RECRUITMENT_SITE_LOGO_CONTENT_TYPE
                        )
                );
    }

    @ExceptionHandler(InvalidRecruitmentSiteLogoExtensionException.class)
    public ResponseEntity<ErrorResponse<String>> handleInvalidRecruitmentSiteLogoExtensionException(InvalidRecruitmentSiteLogoExtensionException exception) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.INVALID_RECRUITMENT_SITE_LOGO_EXTENSION
                        )
                );
    }

    @ExceptionHandler(InvalidRecruitmentSiteLogoSizeException.class)
    public ResponseEntity<ErrorResponse<String>> handleInvalidRecruitmentSiteLogoSizeException(InvalidRecruitmentSiteLogoSizeException exception) {

        return ResponseEntity
                .status(HttpStatus.CONTENT_TOO_LARGE)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.INVALID_RECRUITMENT_SITE_LOGO_SIZE
                        )
                );
    }

    @ExceptionHandler(RecruitmentSiteLogMissingException.class)
    public ResponseEntity<ErrorResponse<String>> handleRecruitmentSiteLogMissingException(RecruitmentSiteLogMissingException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MISSING_LOGO_FOR_RECRUITMENT_SITE
                        )
                );
    }

    @ExceptionHandler(RecruitmentSiteNotConfiguredException.class)
    public ResponseEntity<ErrorResponse<String>> handleRecruitmentSiteNotConfiguredException(RecruitmentSiteNotConfiguredException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.RECRUITMENT_SITE_NOT_CONFIGURED
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

    // JSON deserialization exception
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse<String>> handleHttpMessageNotReadableException() {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.createErrorResponse(
                                "Malformed JSON request",
                                ErrorCode.INVALID_REQUEST_FORMAT
                        )
                );
    }

    // Max upload size
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse<String>> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {

        return ResponseEntity
                .status(HttpStatus.CONTENT_TOO_LARGE)
                .body(
                        ErrorResponse.createErrorResponse(
                                "File size exceeds maximum allowed limit",
                                ErrorCode.FILE_TOO_LARGE
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
