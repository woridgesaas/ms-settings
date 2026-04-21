package org.woridge.settings_management.service.implemetation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.woridge.settings_management.dto.exceptional_leave.*;
import org.woridge.settings_management.exception.exceptional_leave.ExceptionalLeaveNotFoundException;
import org.woridge.settings_management.mapper.ExceptionalLeaveMapper;
import org.woridge.settings_management.model.ExceptionalLeave;
import org.woridge.settings_management.repository.ExceptionalLeaveRepository;
import org.woridge.settings_management.service.ExceptionalLeaveService;
import org.woridge.settings_management.service.ExceptionalLeaveValidator;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExceptionalLeaveServiceImpl implements ExceptionalLeaveService {

    private final ExceptionalLeaveRepository exceptionalLeaveRepository;
    private final ExceptionalLeaveValidator exceptionalLeaveValidator;

    @Override
    public void updateExceptionalLeaves(UpdateExceptionalLeavesRequest updateExceptionalLeavesRequest) {

        for (UpdateExceptionalLeaveItem updateExceptionalLeaveItem : updateExceptionalLeavesRequest.exceptionalLeaves()) {

            ExceptionalLeave currentExceptionalLeave = exceptionalLeaveRepository.findById(updateExceptionalLeaveItem.id()).orElseThrow(() ->
                    new ExceptionalLeaveNotFoundException("Exceptional leave with id : " + updateExceptionalLeaveItem.id() + " not found"));

            if (exceptionalLeaveValidator.isExceptionalLeaveUnchanged(currentExceptionalLeave, updateExceptionalLeaveItem)) {

                continue;
            }

            exceptionalLeaveValidator.validateGrantedDaysGreaterThanLegalBalance(
                    updateExceptionalLeaveItem.grantedDays(),
                    currentExceptionalLeave.getLegalBalance()
            );

            ExceptionalLeaveMapper.updateExceptionalLeave(currentExceptionalLeave, updateExceptionalLeaveItem);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetAllExceptionalLeavesResponse> getAllExceptionalLeaves() {

        return exceptionalLeaveRepository
                .findAll()
                .stream()
                .map(ExceptionalLeaveMapper::toGetAllExceptionalLeavesResponse)
                .toList();
    }
}
