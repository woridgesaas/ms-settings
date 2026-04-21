package org.woridge.settings_management.mapper;

import org.woridge.settings_management.dto.exceptional_leave.*;
import org.woridge.settings_management.model.ExceptionalLeave;

public class ExceptionalLeaveMapper {

    public static GetAllExceptionalLeavesResponse toGetAllExceptionalLeavesResponse(ExceptionalLeave exceptionalLeave) {

        return GetAllExceptionalLeavesResponse
                .builder()
                .id(exceptionalLeave.getId())
                .name(exceptionalLeave.getName())
                .legalBalance(exceptionalLeave.getLegalBalance())
                .grantedDays(exceptionalLeave.getGrantedDays())
                .build();
    }

    public static void updateExceptionalLeave(ExceptionalLeave currentExceptionalLeave, UpdateExceptionalLeaveItem updateExceptionalLeaveItem) {

        currentExceptionalLeave.setGrantedDays(updateExceptionalLeaveItem.grantedDays());
    }
}
