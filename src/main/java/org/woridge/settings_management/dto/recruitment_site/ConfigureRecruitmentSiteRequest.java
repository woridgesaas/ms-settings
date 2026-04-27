package org.woridge.settings_management.dto.recruitment_site;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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

        @NotBlank(message = "Description is required")
        @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
        String description,

        @NotBlank(message = "Founded year is required")
        @Pattern(
                regexp = "^\\d{4}$",
                message = "Founded year must be a valid 4 digit"
        )
        String foundedYear,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Phone is required")
        @Pattern(
                regexp = "^(\\+212|0)([567])[0-9]{8}$",
                message = "Phone must be a valid Moroccan number"
        )
        String phone,

        @NotBlank(message = "Address is required")
        @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
        String address,

        @NotBlank(message = "LinkedIn URL is required")
        @Pattern(
                regexp = "^https://([a-z]{2,3}\\.)?linkedin\\.com/.*$",
                message = "LinkedIn URL must be valid"
        )
        String linkedinUrl
) {
}
