package org.woridge.settings_management.dto.recruitment_site;

import java.util.UUID;

public record GetRecruitmentSiteResponse(
        UUID id,
        String primaryColor,
        String secondaryColor,
        String logoPath,
        String description
) {
}
