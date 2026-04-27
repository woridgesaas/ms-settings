package org.woridge.settings_management.mapper;

import org.woridge.settings_management.dto.recruitment_site.ConfigureRecruitmentSiteRequest;
import org.woridge.settings_management.dto.recruitment_site.GetRecruitmentSiteResponse;
import org.woridge.settings_management.model.RecruitmentSite;

public class RecruitmentSiteMapper {

    public static RecruitmentSite toRecruitmentSite(ConfigureRecruitmentSiteRequest configureRecruitmentSiteRequest, String logoPath) {

        return RecruitmentSite
                .builder()
                .primaryColor(configureRecruitmentSiteRequest.primaryColor())
                .secondaryColor(configureRecruitmentSiteRequest.secondaryColor())
                .logoPath(logoPath)
                .description(configureRecruitmentSiteRequest.description())
                .foundedYear(configureRecruitmentSiteRequest.foundedYear())
                .email(configureRecruitmentSiteRequest.email())
                .phone(configureRecruitmentSiteRequest.phone())
                .address(configureRecruitmentSiteRequest.address())
                .linkedinUrl(configureRecruitmentSiteRequest.linkedinUrl())
                .build();
    }

    public static void updateRecruitmentSite(RecruitmentSite currentRecruitmentSite, ConfigureRecruitmentSiteRequest configureRecruitmentSiteRequest, String newLogoPath) {

        currentRecruitmentSite.setPrimaryColor(configureRecruitmentSiteRequest.primaryColor());
        currentRecruitmentSite.setSecondaryColor(configureRecruitmentSiteRequest.secondaryColor());
        currentRecruitmentSite.setDescription(configureRecruitmentSiteRequest.description());
        currentRecruitmentSite.setFoundedYear(configureRecruitmentSiteRequest.foundedYear());
        currentRecruitmentSite.setEmail(configureRecruitmentSiteRequest.email());
        currentRecruitmentSite.setPhone(configureRecruitmentSiteRequest.phone());
        currentRecruitmentSite.setAddress(configureRecruitmentSiteRequest.address());
        currentRecruitmentSite.setLinkedinUrl(configureRecruitmentSiteRequest.linkedinUrl());

        if (newLogoPath != null) {

            currentRecruitmentSite.setLogoPath(newLogoPath);
        }
    }

    public static GetRecruitmentSiteResponse toGetRecruitmentSiteResponse(RecruitmentSite currentRecruitmentSite) {

        return new GetRecruitmentSiteResponse(
                currentRecruitmentSite.getId(),
                currentRecruitmentSite.getPrimaryColor(),
                currentRecruitmentSite.getSecondaryColor(),
                currentRecruitmentSite.getDescription(),
                currentRecruitmentSite.getFoundedYear(),
                currentRecruitmentSite.getEmail(),
                currentRecruitmentSite.getPhone(),
                currentRecruitmentSite.getAddress(),
                currentRecruitmentSite.getLinkedinUrl()
        );
    }
}
