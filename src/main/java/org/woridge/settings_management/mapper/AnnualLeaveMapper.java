package org.woridge.settings_management.mapper;

import org.woridge.settings_management.dto.annual_leave.ConfigureAnnualBalanceRequest;
import org.woridge.settings_management.dto.annual_leave.GetAnnualBalanceResponse;
import org.woridge.settings_management.model.AnnualLeave;

public class AnnualLeaveMapper {

    public static AnnualLeave toAnnualLeave(ConfigureAnnualBalanceRequest configureAnnualBalanceRequest) {

        return AnnualLeave
                .builder()
                .balance(configureAnnualBalanceRequest.balance())
                .build();
    }

    public static void updateAnnualLeave(AnnualLeave currentAnnualLeave, ConfigureAnnualBalanceRequest configureAnnualBalanceRequest) {

        currentAnnualLeave.setBalance(configureAnnualBalanceRequest.balance());
    }

    public static GetAnnualBalanceResponse toGetAnnualBalanceResponse(AnnualLeave annualLeave) {

        return new GetAnnualBalanceResponse(
                annualLeave.getId(),
                annualLeave.getBalance()
        );
    }
}
