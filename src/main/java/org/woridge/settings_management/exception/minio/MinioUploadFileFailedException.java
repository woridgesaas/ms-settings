package org.woridge.settings_management.exception.minio;

public class MinioUploadFileFailedException extends RuntimeException {

    public MinioUploadFileFailedException(String message) {

        super(message);
    }
}
