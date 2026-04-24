package org.woridge.settings_management.service.implemetation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.woridge.settings_management.exception.document_template.DocumentTemplateConversionFailedException;
import org.woridge.settings_management.service.DocumentTemplateConversion;

@Service
public class DocumentTemplateConversionImpl implements DocumentTemplateConversion {

    @Override
    public byte[] convertMultipartFileToBytes(MultipartFile documentTemplate) {

        try {

            return documentTemplate.getBytes();

        } catch (Exception exception) {

            throw new DocumentTemplateConversionFailedException("Failed to convert file '" + documentTemplate.getOriginalFilename() + " to PDF");
        }
    }
}
