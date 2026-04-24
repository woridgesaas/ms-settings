package org.woridge.settings_management.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "document-template")
@Getter
@Setter
public class DocumentTemplateProperties {

    private long maxSizeMb;
    private String allowedContentType;
    private String allowedExtension;

    public long getMaxSizeBytes() {

        return maxSizeMb * 1024 * 1024;
    }
}
