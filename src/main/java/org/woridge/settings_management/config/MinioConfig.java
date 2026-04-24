package org.woridge.settings_management.config;

import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.woridge.settings_management.properties.MinioProperties;

@Configuration
@AllArgsConstructor
public class MinioConfig {

    private final MinioProperties minioProperties;

    @Bean
    public MinioClient customMinioClient() {

        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
