package org.woridge.settings_management.dto.annual_leave;

import java.util.UUID;

public record GetAnnualBalanceResponse(
        UUID id,
        int balance
) {
}
