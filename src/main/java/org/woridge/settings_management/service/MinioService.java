package org.woridge.settings_management.service;

public interface MinioService {

    String uploadFile(String tenantName, String fileName, byte[] content, String contentType);

    String generateFilePreviewUrl(String filePath);

    void deleteFile(String filePath);
}
