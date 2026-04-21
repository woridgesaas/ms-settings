package org.woridge.settings_management.service.implemetation;

import org.springframework.stereotype.Service;
import org.woridge.settings_management.dto.annual_leave.ConfigureAnnualBalanceRequest;
import org.woridge.settings_management.model.AnnualLeave;
import org.woridge.settings_management.service.AnnualLeaveValidator;

@Service
public class AnnualLeaveValidatorImpl implements AnnualLeaveValidator {

    @Override
    public boolean isAnnualLeaveUnchanged(AnnualLeave currentAnnualLeave, ConfigureAnnualBalanceRequest configureAnnualBalanceRequest) {
        return currentAnnualLeave.getBalance() == configureAnnualBalanceRequest.balance();
    }
}
