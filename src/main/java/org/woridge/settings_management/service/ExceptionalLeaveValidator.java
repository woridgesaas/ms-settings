package org.woridge.settings_management.service;

import org.woridge.settings_management.dto.exceptional_leave.UpdateExceptionalLeaveItem;
import org.woridge.settings_management.model.ExceptionalLeave;

public interface ExceptionalLeaveValidator {

    void validateGrantedDaysGreaterThanLegalBalance(int grantedDays, int legalBalance);

    boolean isExceptionalLeaveUnchanged(ExceptionalLeave currentExceptionalLeave, UpdateExceptionalLeaveItem updateExceptionalLeaveItem);
}
