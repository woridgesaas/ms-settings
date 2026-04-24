package org.woridge.settings_management.service;

public interface MinioService {

    String uploadPdfDocumentTemplate(String tenantName, String fileName, byte[] content);

    String generateDocumentTemplatePreviewUrl(String documentTemplatePath);

    void deleteDocumentTemplate(String documentTemplatePath);
}
