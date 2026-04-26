package org.woridge.settings_management.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.woridge.settings_management.dto.SuccessResponse;
import org.woridge.settings_management.dto.recruitment_site.ConfigureRecruitmentSiteRequest;
import org.woridge.settings_management.dto.recruitment_site.GetRecruitmentSiteResponse;
import org.woridge.settings_management.service.RecruitmentSiteService;

@RestController
@RequestMapping("/recruitment-sites")
@AllArgsConstructor
public class RecruitmentSiteController {

    private final RecruitmentSiteService recruitmentSiteService;

    @PutMapping
    public ResponseEntity<Void> configureRecruitmentSite(
            @Valid @ModelAttribute ConfigureRecruitmentSiteRequest configureRecruitmentSiteRequest,
            @RequestPart(value = "logo", required = false) MultipartFile logo
    ) {

        recruitmentSiteService.configureRecruitmentSite(configureRecruitmentSiteRequest, logo);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<GetRecruitmentSiteResponse>> getRecruitmentSite() {

        return ResponseEntity.ok(
                SuccessResponse.createSuccessResponse(
                        "Recruitment site fetched successfully",
                        recruitmentSiteService.getRecruitmentSite()
                )
        );
    }
}
