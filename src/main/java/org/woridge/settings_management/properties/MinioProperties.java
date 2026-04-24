package org.woridge.settings_management.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties(prefix = "minio")
@Getter
@Setter
public class MinioProperties {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String serviceIdentifier;
    private Duration presignedUrlExpiry;
}
