package org.woridge.settings_management.dto.document_template;

import org.woridge.settings_management.enumeration.DocumentCategory;

import java.util.UUID;

public record UploadDocumentTemplatesResponse(
        UUID id,
        DocumentCategory category,
        String path
) {
}
