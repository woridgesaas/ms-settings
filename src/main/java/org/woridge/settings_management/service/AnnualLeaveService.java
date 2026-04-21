package org.woridge.settings_management.service;

import org.woridge.settings_management.dto.annual_leave.ConfigureAnnualBalanceRequest;
import org.woridge.settings_management.dto.annual_leave.GetAnnualBalanceResponse;

public interface AnnualLeaveService {

    void configureAnnualBalance(ConfigureAnnualBalanceRequest configureAnnualBalanceRequest);

    GetAnnualBalanceResponse getAnnualBalance();
}
