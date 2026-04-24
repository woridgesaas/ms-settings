package org.woridge.settings_management.dto.document_template;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import org.woridge.settings_management.enumeration.DocumentCategory;

public record UploadDocumentTemplateItem(

        @NotNull(message = "Document category is required")
        DocumentCategory category,

        @NotNull(message = "Document template is required")
        MultipartFile documentTemplate
) {
}