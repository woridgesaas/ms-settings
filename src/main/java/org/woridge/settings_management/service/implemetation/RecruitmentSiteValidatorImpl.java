package org.woridge.settings_management.service.implemetation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.woridge.settings_management.dto.recruitment_site.ConfigureRecruitmentSiteRequest;
import org.woridge.settings_management.exception.recruitment_site.InvalidRecruitmentSiteLogoContentTypeException;
import org.woridge.settings_management.exception.recruitment_site.InvalidRecruitmentSiteLogoExtensionException;
import org.woridge.settings_management.exception.recruitment_site.InvalidRecruitmentSiteLogoSizeException;
import org.woridge.settings_management.model.RecruitmentSite;
import org.woridge.settings_management.properties.RecruitmentSiteLogoProperties;
import org.woridge.settings_management.service.RecruitmentSiteValidator;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RecruitmentSiteValidatorImpl implements RecruitmentSiteValidator {

    private final RecruitmentSiteLogoProperties recruitmentSiteLogoProperties;

    @Override
    public void validateRecruitmentSiteLogo(MultipartFile logo) {

        if (logo.getSize() > recruitmentSiteLogoProperties.getMaxSizeBytes()) {

            throw new InvalidRecruitmentSiteLogoSizeException("Logo exceeds the maximum allowed size of " + recruitmentSiteLogoProperties.getMaxSizeMb() + " MB");
        }

        String contentType = logo.getContentType();

        if (contentType == null ||
                recruitmentSiteLogoProperties
                        .getAllowedContentTypes()
                        .stream()
                        .noneMatch(allowed -> allowed.equalsIgnoreCase(contentType))) {

            throw new InvalidRecruitmentSiteLogoContentTypeException("Logo content type must be one of : " + recruitmentSiteLogoProperties.getAllowedContentTypes());
        }

        String filename = logo.getOriginalFilename();

        if (filename == null ||
                recruitmentSiteLogoProperties
                        .getAllowedExtensions()
                        .stream()
                        .noneMatch(ext -> filename.toLowerCase().endsWith(ext))) {

            throw new InvalidRecruitmentSiteLogoExtensionException("Logo extension must be one of : " + recruitmentSiteLogoProperties.getAllowedExtensions());
        }

    }

    @Override
    public boolean isRecruitmentSiteUnchanged(RecruitmentSite currentRecruitmentSite, ConfigureRecruitmentSiteRequest configureRecruitmentSiteRequest, MultipartFile logo) {

        return Objects.equals(currentRecruitmentSite.getPrimaryColor(), configureRecruitmentSiteRequest.primaryColor())
                && Objects.equals(currentRecruitmentSite.getSecondaryColor(), configureRecruitmentSiteRequest.secondaryColor())
                && Objects.equals(currentRecruitmentSite.getDescription(), configureRecruitmentSiteRequest.description())
                && (logo == null || logo.isEmpty());
    }
}
