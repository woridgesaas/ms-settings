package org.woridge.settings_management.service;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentTemplateConversion {

    byte[] convertMultipartFileToBytes(MultipartFile documentTemplate);
}
