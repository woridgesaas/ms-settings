package org.woridge.settings_management.service.implemetation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.woridge.settings_management.dto.recruitment_site.ConfigureRecruitmentSiteRequest;
import org.woridge.settings_management.dto.recruitment_site.GetRecruitmentSiteResponse;
import org.woridge.settings_management.exception.recruitment_site.RecruitmentSiteLogMissingException;
import org.woridge.settings_management.exception.recruitment_site.RecruitmentSiteNotConfiguredException;
import org.woridge.settings_management.mapper.RecruitmentSiteMapper;
import org.woridge.settings_management.model.RecruitmentSite;
import org.woridge.settings_management.repository.RecruitmentSiteRepository;
import org.woridge.settings_management.service.FileConversion;
import org.woridge.settings_management.service.MinioService;
import org.woridge.settings_management.service.RecruitmentSiteService;
import org.woridge.settings_management.service.RecruitmentSiteValidator;
import org.woridge.settings_management.utils.FileUtils;
import org.woridge.settings_management.utils.RequestUtils;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class RecruitmentSiteServiceImpl implements RecruitmentSiteService {

    private final RecruitmentSiteRepository recruitmentSiteRepository;
    private final RecruitmentSiteValidator recruitmentSiteValidator;
    private final HttpServletRequest httpServletRequest;
    private final MinioService minioService;
    private final FileConversion fileConversion;

    @Override
    public void configureRecruitmentSite(ConfigureRecruitmentSiteRequest configureRecruitmentSiteRequest, MultipartFile logo) {

        String tenantName = RequestUtils.extractSubdomainFromHeaderRequest(httpServletRequest);

        Optional<RecruitmentSite> currentRecruitmentSite = recruitmentSiteRepository.findFirstBy();

        currentRecruitmentSite.ifPresentOrElse(
                recruitmentSite -> updateRecruitmentSite(recruitmentSite, configureRecruitmentSiteRequest, logo, tenantName),
                () -> addRecruitmentSite(configureRecruitmentSiteRequest, logo, tenantName)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public GetRecruitmentSiteResponse getRecruitmentSite() {

        RecruitmentSite currentRecruitmentSite = recruitmentSiteRepository.findFirstBy().orElseThrow(() ->
                new RecruitmentSiteNotConfiguredException("Recruitment site not configured"));

        return RecruitmentSiteMapper.toGetRecruitmentSiteResponse(currentRecruitmentSite);
    }

    // Helper methods
    private void addRecruitmentSite(ConfigureRecruitmentSiteRequest configureRecruitmentSiteRequest, MultipartFile logo, String tenantName) {

        if (logo == null || logo.isEmpty()) {

            throw new RecruitmentSiteLogMissingException("Logo is required when configuring the recruitment site");
        }

        recruitmentSiteValidator.validateRecruitmentSiteLogo(logo);

        String logoPath = uploadLogo(logo, tenantName);

        recruitmentSiteRepository.save(RecruitmentSiteMapper.toRecruitmentSite(configureRecruitmentSiteRequest, logoPath));
    }

    private void updateRecruitmentSite(RecruitmentSite currentRecruitmentSite, ConfigureRecruitmentSiteRequest configureRecruitmentSiteRequest, MultipartFile logo, String tenantName) {

        if (recruitmentSiteValidator.isRecruitmentSiteUnchanged(currentRecruitmentSite, configureRecruitmentSiteRequest, logo)) {

            return;
        }

        String newLogoPath = null;

        if (logo != null && !logo.isEmpty()) {

            recruitmentSiteValidator.validateRecruitmentSiteLogo(logo);

            minioService.deleteFile(currentRecruitmentSite.getLogoPath());

            newLogoPath = uploadLogo(logo, tenantName);
        }

        RecruitmentSiteMapper.updateRecruitmentSite(currentRecruitmentSite, configureRecruitmentSiteRequest, newLogoPath);
    }

    private String uploadLogo(MultipartFile logo, String tenantName) {

        String fileName = FileUtils.buildFileName("logo", FileUtils.extractExtension(logo.getOriginalFilename()));

        return minioService.uploadFile(
                tenantName,
                fileName,
                fileConversion.convertMultipartFileToBytes(logo),
                logo.getContentType()
        );
    }
}
