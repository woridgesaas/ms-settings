package org.woridge.settings_management.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "recruitment-site.logo")
@Getter
@Setter
public class RecruitmentSiteLogoProperties {

    private long maxSizeMb;
    private String allowedContentTypes;
    private String allowedExtensions;

    public long getMaxSizeBytes() {

        return maxSizeMb * 1024 * 1024;
    }

    public List<String> getAllowedContentTypes() {

        return Arrays.asList(allowedContentTypes.split(","));
    }

    public List<String> getAllowedExtensions() {

        return Arrays.asList(allowedExtensions.split(","));
    }
}
