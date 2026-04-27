package org.woridge.settings_management.dto.file;

public record FileResourceResponse(
        byte[] content,
        String contentType
) {
}
