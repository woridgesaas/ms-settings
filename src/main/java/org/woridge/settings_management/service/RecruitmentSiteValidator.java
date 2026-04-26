package org.woridge.settings_management.service;

import org.springframework.web.multipart.MultipartFile;
import org.woridge.settings_management.dto.recruitment_site.ConfigureRecruitmentSiteRequest;
import org.woridge.settings_management.model.RecruitmentSite;

public interface RecruitmentSiteValidator {

    void validateRecruitmentSiteLogo(MultipartFile logo);

    boolean isRecruitmentSiteUnchanged(RecruitmentSite currentRecruitmentSite, ConfigureRecruitmentSiteRequest configureRecruitmentSiteRequest, MultipartFile logo);
}
