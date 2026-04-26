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
                .build();
    }

    public static void updateRecruitmentSite(RecruitmentSite currentRecruitmentSite, ConfigureRecruitmentSiteRequest configureRecruitmentSiteRequest, String newLogoPath) {

        currentRecruitmentSite.setPrimaryColor(configureRecruitmentSiteRequest.primaryColor());
        currentRecruitmentSite.setSecondaryColor(configureRecruitmentSiteRequest.secondaryColor());
        currentRecruitmentSite.setDescription(configureRecruitmentSiteRequest.description());

        if (newLogoPath != null) {

            currentRecruitmentSite.setLogoPath(newLogoPath);
        }
    }

    public static GetRecruitmentSiteResponse toGetRecruitmentSiteResponse(RecruitmentSite currentRecruitmentSite) {

        return new GetRecruitmentSiteResponse(
                currentRecruitmentSite.getId(),
                currentRecruitmentSite.getPrimaryColor(),
                currentRecruitmentSite.getSecondaryColor(),
                currentRecruitmentSite.getLogoPath(),
                currentRecruitmentSite.getDescription()
        );
    }
}
