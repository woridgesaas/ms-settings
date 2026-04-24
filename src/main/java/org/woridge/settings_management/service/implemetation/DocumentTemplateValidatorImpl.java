package org.woridge.settings_management.service.implemetation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.woridge.settings_management.properties.DocumentTemplateProperties;
import org.woridge.settings_management.exception.document_template.InvalidDocumentTemplateContentTypeException;
import org.woridge.settings_management.exception.document_template.InvalidDocumentTemplateExtensionException;
import org.woridge.settings_management.exception.document_template.InvalidDocumentTemplateSizeException;
import org.woridge.settings_management.service.DocumentTemplateValidator;

@Service
@AllArgsConstructor
public class DocumentTemplateValidatorImpl implements DocumentTemplateValidator {

    private final DocumentTemplateProperties documentTemplateProperties;

    public void validateDocumentTemplate(MultipartFile documentTemplate) {

        if (documentTemplate.getSize() > documentTemplateProperties.getMaxSizeBytes()) {

            throw new InvalidDocumentTemplateSizeException("Document template exceeds the maximum allowed size of " + documentTemplateProperties.getMaxSizeMb() + " MB");
        }

        String contentType = documentTemplate.getContentType();

        if (contentType == null || !contentType.equalsIgnoreCase(documentTemplateProperties.getAllowedContentType())) {

            throw new InvalidDocumentTemplateContentTypeException("Content type must be " + documentTemplateProperties.getAllowedContentType());
        }

        String filename = documentTemplate.getOriginalFilename();

        if (filename == null || !filename.toLowerCase().endsWith(documentTemplateProperties.getAllowedExtension())) {

            throw new InvalidDocumentTemplateExtensionException("Document template extension must be " + documentTemplateProperties.getAllowedExtension());
        }
    }
}
