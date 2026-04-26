package org.woridge.settings_management.service;

import org.springframework.web.multipart.MultipartFile;
import org.woridge.settings_management.dto.recruitment_site.ConfigureRecruitmentSiteRequest;
import org.woridge.settings_management.dto.recruitment_site.GetRecruitmentSiteResponse;

public interface RecruitmentSiteService {

    void configureRecruitmentSite(ConfigureRecruitmentSiteRequest configureRecruitmentSiteRequest, MultipartFile logo);

    GetRecruitmentSiteResponse getRecruitmentSite();
}
