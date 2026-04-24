package org.woridge.settings_management.dto.document_template;

public record DocumentTemplateLocation(
        String bucketName,
        String fileName
) {
}
