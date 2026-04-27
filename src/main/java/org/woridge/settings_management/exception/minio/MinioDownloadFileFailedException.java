package org.woridge.settings_management.exception.minio;

public class MinioDownloadFileFailedException extends RuntimeException {

    public MinioDownloadFileFailedException(String message) {

        super(message);
    }
}
