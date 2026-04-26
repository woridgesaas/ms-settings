package org.woridge.settings_management.dto.file;

public record FileLocation(
        String bucketName,
        String fileName
) {
}
