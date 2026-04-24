package org.woridge.settings_management.service;

import org.woridge.settings_management.dto.document_template.PreviewDocumentTemplateResponse;
import org.woridge.settings_management.dto.document_template.UploadDocumentTemplatesRequest;
import org.woridge.settings_management.dto.document_template.UploadDocumentTemplatesResponse;

import java.util.List;
import java.util.UUID;

public interface DocumentTemplateService {

    List<UploadDocumentTemplatesResponse> uploadDocumentTemplates(UploadDocumentTemplatesRequest uploadDocumentTemplatesRequest);

    PreviewDocumentTemplateResponse previewDocumentTemplate(UUID documentTemplateId);

    void deleteDocumentTemplate(UUID documentTemplateId);
}
