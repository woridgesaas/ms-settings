package org.woridge.settings_management.dto.recruitment_site;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ConfigureRecruitmentSiteRequest(

        @NotBlank(message = "Primary color is required")
        @Pattern(
                regexp = "^(#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})|rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)|[a-zA-Z]+)$",
                message = "Primary color must be a valid Hexadecimal, RGB or color name"
        )
        String primaryColor,

        @NotBlank(message = "Secondary color is required")
        @Pattern(
                regexp = "^(#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})|rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)|[a-zA-Z]+)$",
                message = "Secondary color must be a valid Hexadecimal, RGB or color name"
        )
        String secondaryColor,

        String description
) {
}
