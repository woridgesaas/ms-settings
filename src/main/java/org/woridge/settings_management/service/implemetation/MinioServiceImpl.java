package org.woridge.settings_management.service.implemetation;

import io.minio.*;
import io.minio.http.Method;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.woridge.settings_management.dto.file.FileLocation;
import org.woridge.settings_management.exception.document_template.InvalidDocumentTemplatePathException;
import org.woridge.settings_management.exception.minio.MinioDeleteFileFailedException;
import org.woridge.settings_management.exception.minio.MinioDownloadFileFailedException;
import org.woridge.settings_management.exception.minio.MinioUploadFileFailedException;
import org.woridge.settings_management.exception.minio.MinioPresignedUrlGenerationFailedException;
import org.woridge.settings_management.properties.MinioProperties;
import org.woridge.settings_management.service.MinioBucketService;
import org.woridge.settings_management.service.MinioService;
import org.woridge.settings_management.utils.MinioUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;
    private final MinioBucketService minioBucketService;
    private final MinioProperties minioProperties;

    @Override
    public String uploadFile(String tenantName, String fileName, byte[] content, String contentType) {

        String bucketName = MinioUtils.buildBucketName(tenantName, minioProperties.getServiceIdentifier());

        minioBucketService.createBucket(bucketName);

        try {

            minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(
                                    new ByteArrayInputStream(content),
                                    content.length,
                                    -1
                            )
                            .contentType(contentType)
                            .build()
            );

            return bucketName + "/" + fileName;

        } catch (Exception exception) {

            throw new MinioUploadFileFailedException("Failed to upload file : " + fileName + " to bucket : " + bucketName);
        }
    }

    @Override
    public String generateFilePreviewUrl(String filePath) {

        FileLocation fileLocation = getFilePathParts(filePath);

        try {

            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs
                            .builder()
                            .method(Method.GET)
                            .bucket(fileLocation.bucketName())
                            .object(fileLocation.fileName())
                            .expiry((int) minioProperties.getPresignedUrlExpiry().getSeconds(), TimeUnit.SECONDS)
                            .build()
            );

        } catch (Exception exception) {

            throw new MinioPresignedUrlGenerationFailedException("Failed to generate document template preview URL for file : " + fileLocation.fileName() + " in bucket : " + fileLocation.bucketName());
        }
    }

    @Override
    public void deleteFile(String filePath) {

        FileLocation fileLocation = getFilePathParts(filePath);

        try {

            minioClient.removeObject(
                    RemoveObjectArgs
                            .builder()
                            .bucket(fileLocation.bucketName())
                            .object(fileLocation.fileName())
                            .build()
            );

        } catch (Exception exception) {

            throw new MinioDeleteFileFailedException("Failed to delete file : " + fileLocation.fileName() + " from bucket : " + fileLocation.bucketName());
        }
    }

    @Override
    public byte[] downloadFile(String filePath) {

        FileLocation fileLocation = getFilePathParts(filePath);

        try {

            InputStream stream = minioClient.getObject(
                    GetObjectArgs
                            .builder()
                            .bucket(fileLocation.bucketName())
                            .object(fileLocation.fileName())
                            .build()
            );

            return stream.readAllBytes();

        } catch (Exception exception) {

            throw new MinioDownloadFileFailedException("Failed to download file : " + fileLocation.fileName() + " from bucket : " + fileLocation.bucketName());
        }
    }

    // Helper method
    private FileLocation getFilePathParts(String documentTemplatePath) {

        String[] documentTemplatePathParts = documentTemplatePath.split("/", 2);

        if (documentTemplatePathParts.length < 2) {

            throw new InvalidDocumentTemplatePathException("Invalid document template path : " + documentTemplatePath);
        }

        return new FileLocation(
                documentTemplatePathParts[0],
                documentTemplatePathParts[1]
        );
    }
}
