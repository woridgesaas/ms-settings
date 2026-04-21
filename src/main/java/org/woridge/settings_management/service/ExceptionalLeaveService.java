package org.woridge.settings_management.service;

import org.woridge.settings_management.dto.exceptional_leave.GetAllExceptionalLeavesResponse;
import org.woridge.settings_management.dto.exceptional_leave.UpdateExceptionalLeavesRequest;

import java.util.List;

public interface ExceptionalLeaveService {

    void updateExceptionalLeaves(UpdateExceptionalLeavesRequest updateExceptionalLeavesRequest);

    List<GetAllExceptionalLeavesResponse> getAllExceptionalLeaves();
}
