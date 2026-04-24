package org.woridge.settings_management.dto.document_template;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UploadDocumentTemplatesRequest(

        @NotNull(message = "Document list is required")
        @NotEmpty(message = "Document list cannot be empty")
        List<UploadDocumentTemplateItem> documentTemplateItems
) {
}
