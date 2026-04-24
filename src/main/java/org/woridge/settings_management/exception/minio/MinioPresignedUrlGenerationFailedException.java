package org.woridge.settings_management.exception.minio;

public class MinioPresignedUrlGenerationFailedException extends RuntimeException {

    public MinioPresignedUrlGenerationFailedException(String message) {

        super(message);
    }
}
