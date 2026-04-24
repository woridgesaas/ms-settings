package org.woridge.settings_management.exception.minio;

public class MinioBucketCheckFailedException extends RuntimeException {

    public MinioBucketCheckFailedException(String message) {

        super(message);
    }
}
