package org.woridge.settings_management.service.implemetation;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.woridge.settings_management.dto.document_template.DocumentTemplateLocation;
import org.woridge.settings_management.exception.document_template.InvalidDocumentTemplatePathException;
import org.woridge.settings_management.exception.minio.MinioDeleteDocumentTemplateFailedException;
import org.woridge.settings_management.exception.minio.MinioUploadDocumentTemplateFailedException;
import org.woridge.settings_management.exception.minio.MinioPresignedUrlGenerationFailedException;
import org.woridge.settings_management.properties.MinioProperties;
import org.woridge.settings_management.service.MinioBucketService;
import org.woridge.settings_management.service.MinioService;
import org.woridge.settings_management.utils.MinioUtils;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;
    private final MinioBucketService minioBucketService;
    private final MinioProperties minioProperties;

    @Override
    public String uploadPdfDocumentTemplate(String tenantName, String fileName, byte[] content) {

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
                            .contentType("application/pdf")
                            .build()
            );

            return bucketName + "/" + fileName;

        } catch (Exception exception) {

            throw new MinioUploadDocumentTemplateFailedException("Failed to upload PDF document : " + fileName + " to bucket : '" + bucketName);
        }
    }

    @Override
    public String generateDocumentTemplatePreviewUrl(String documentTemplatePath) {

        DocumentTemplateLocation documentTemplateLocation = getDocumentTemplatePathParts(documentTemplatePath);

        try {

            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs
                            .builder()
                            .method(Method.GET)
                            .bucket(documentTemplateLocation.bucketName())
                            .object(documentTemplateLocation.fileName())
                            .expiry((int) minioProperties.getPresignedUrlExpiry().getSeconds(), TimeUnit.SECONDS)
                            .build()
            );

        } catch (Exception exception) {

            throw new MinioPresignedUrlGenerationFailedException("Failed to generate document template preview URL for file : " + documentTemplateLocation.fileName() + " in bucket : " + documentTemplateLocation.bucketName());
        }
    }

    @Override
    public void deleteDocumentTemplate(String documentTemplatePath) {

        DocumentTemplateLocation documentTemplateLocation = getDocumentTemplatePathParts(documentTemplatePath);

        try {
            minioClient.removeObject(
                    RemoveObjectArgs
                            .builder()
                            .bucket(documentTemplateLocation.bucketName())
                            .object(documentTemplateLocation.fileName())
                            .build()
            );

        } catch (Exception exception) {

            throw new MinioDeleteDocumentTemplateFailedException("Failed to delete document template : " + documentTemplateLocation.fileName() + " from bucket : " + documentTemplateLocation.bucketName());
        }
    }

    // Helper method
    private DocumentTemplateLocation getDocumentTemplatePathParts(String documentTemplatePath) {

        String[] documentTemplatePathParts = documentTemplatePath.split("/", 2);

        if (documentTemplatePathParts.length < 2) {

            throw new InvalidDocumentTemplatePathException("Invalid document template path : " + documentTemplatePath);
        }

        return new DocumentTemplateLocation(
                documentTemplatePathParts[0],
                documentTemplatePathParts[1]
        );
    }
}
