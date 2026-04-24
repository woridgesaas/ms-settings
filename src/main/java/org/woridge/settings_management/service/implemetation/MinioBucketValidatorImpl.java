package org.woridge.settings_management.service.implemetation;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.woridge.settings_management.exception.minio.MinioBucketCheckFailedException;
import org.woridge.settings_management.service.MinioBucketValidator;

@Service
@AllArgsConstructor
public class MinioBucketValidatorImpl implements MinioBucketValidator {

    private final MinioClient minioClient;

    @Override
    public boolean isBucketExists(String bucketName) {

        try {

            return minioClient.bucketExists(
                    BucketExistsArgs
                            .builder()
                            .bucket(bucketName)
                            .build()
            );

        } catch (Exception exception) {

            throw new MinioBucketCheckFailedException("Failed to check if bucket : " + bucketName + "  exists");
        }
    }
}
