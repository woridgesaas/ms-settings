package org.woridge.settings_management.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileConversion {

    byte[] convertMultipartFileToBytes(MultipartFile documentTemplate);
}
