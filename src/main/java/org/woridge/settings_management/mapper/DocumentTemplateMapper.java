package org.woridge.settings_management.mapper;

import org.woridge.settings_management.dto.document_template.PreviewDocumentTemplateResponse;
import org.woridge.settings_management.dto.document_template.UploadDocumentTemplateItem;
import org.woridge.settings_management.dto.document_template.UploadDocumentTemplatesResponse;
import org.woridge.settings_management.model.DocumentTemplate;

public class DocumentTemplateMapper {

    public static DocumentTemplate toDocumentTemplate(UploadDocumentTemplateItem uploadDocumentTemplateItem, String documentTemplatePath) {

        return DocumentTemplate
                .builder()
                .category(uploadDocumentTemplateItem.category())
                .path(documentTemplatePath)
                .build();
    }

    public static UploadDocumentTemplatesResponse toUploadDocumentTemplatesResponse(DocumentTemplate documentTemplate) {

        return new UploadDocumentTemplatesResponse(
                documentTemplate.getId(),
                documentTemplate.getCategory(),
                documentTemplate.getPath()
        );
    }

    public static PreviewDocumentTemplateResponse toPreviewDocumentTemplateResponse(String previewUrl) {

        return new PreviewDocumentTemplateResponse(
                previewUrl
        );
    }
}
