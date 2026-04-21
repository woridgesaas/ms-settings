package org.woridge.settings_management.service;

import org.woridge.settings_management.dto.annual_leave.ConfigureAnnualBalanceRequest;
import org.woridge.settings_management.model.AnnualLeave;

public interface AnnualLeaveValidator {

    boolean isAnnualLeaveUnchanged(AnnualLeave currentAnnualLeave, ConfigureAnnualBalanceRequest configureAnnualBalanceRequest);
}
