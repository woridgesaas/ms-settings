package org.woridge.settings_management.service.implemetation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.woridge.settings_management.exception.file.FileConversionFailedException;
import org.woridge.settings_management.service.FileConversion;

@Service
public class FileConversionImpl implements FileConversion {

    @Override
    public byte[] convertMultipartFileToBytes(MultipartFile documentTemplate) {

        try {

            return documentTemplate.getBytes();

        } catch (Exception exception) {

            throw new FileConversionFailedException("Failed to convert file '" + documentTemplate.getOriginalFilename());
        }
    }
}
