package org.woridge.settings_management.service.implemetation;

import org.springframework.stereotype.Service;
import org.woridge.settings_management.dto.exceptional_leave.UpdateExceptionalLeaveItem;
import org.woridge.settings_management.exception.exceptional_leave.GrantedDaysLessThanLegalBalanceException;
import org.woridge.settings_management.model.ExceptionalLeave;
import org.woridge.settings_management.service.ExceptionalLeaveValidator;

@Service
public class ExceptionalLeaveValidatorImpl implements ExceptionalLeaveValidator {

    @Override
    public void validateGrantedDaysGreaterThanOrEqualLegalBalance(int grantedDays, int legalBalance) {

        if (grantedDays < legalBalance) {

            throw new GrantedDaysLessThanLegalBalanceException("Granted days must be greater than or equal to legal balance");
        }
    }

    @Override
    public boolean isExceptionalLeaveUnchanged(ExceptionalLeave currentExceptionalLeave, UpdateExceptionalLeaveItem updateExceptionalLeaveItem) {

        return currentExceptionalLeave.getGrantedDays() == updateExceptionalLeaveItem.grantedDays();
    }
}
