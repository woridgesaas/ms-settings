package org.woridge.settings_management.exception.minio;

public class MinioDeleteFileFailedException extends RuntimeException {

    public MinioDeleteFileFailedException(String message) {

        super(message);
    }
}
