package org.woridge.settings_management.dto.exceptional_leave;

import lombok.Builder;
import org.woridge.settings_management.enumeration.ExceptionalLeaveName;

import java.util.UUID;

@Builder
public record GetAllExceptionalLeavesResponse(
        UUID id,
        ExceptionalLeaveName name,
        int legalBalance,
        int grantedDays
) {
}
