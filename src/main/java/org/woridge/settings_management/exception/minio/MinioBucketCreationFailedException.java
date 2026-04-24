package org.woridge.settings_management.exception.minio;

public class MinioBucketCreationFailedException extends RuntimeException {

    public MinioBucketCreationFailedException(String message) {

        super(message);
    }
}
