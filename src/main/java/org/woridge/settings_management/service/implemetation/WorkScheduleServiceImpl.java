package org.woridge.settings_management.service.implemetation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.woridge.settings_management.dto.work_schedule.ConfigureWorkScheduleRequest;
import org.woridge.settings_management.dto.work_schedule.GetWorkScheduleResponse;
import org.woridge.settings_management.exception.work_schedule.WorkScheduleNotConfiguredException;
import org.woridge.settings_management.mapper.WorkScheduleMapper;
import org.woridge.settings_management.model.WorkSchedule;
import org.woridge.settings_management.repository.WorkScheduleRepository;
import org.woridge.settings_management.service.WorkScheduleService;
import org.woridge.settings_management.service.WorkScheduleValidator;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class WorkScheduleServiceImpl implements WorkScheduleService {

    private final WorkScheduleRepository workScheduleRepository;
    private final WorkScheduleValidator workScheduleValidator;

    @Override
    public void configureWorkSchedule(ConfigureWorkScheduleRequest configureWorkScheduleRequest) {

        workScheduleValidator.validateWorkSchedule(configureWorkScheduleRequest);

        Optional<WorkSchedule> currentWorkSchedule = workScheduleRepository.findFirstBy();

        currentWorkSchedule.ifPresentOrElse(
                workSchedule -> updateWorkSchedule(workSchedule, configureWorkScheduleRequest),
                () -> addWorkSchedule(configureWorkScheduleRequest)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public GetWorkScheduleResponse getWorkSchedule() {

        WorkSchedule currentWorkSchedule = workScheduleRepository.findFirstBy().orElseThrow(() ->
                new WorkScheduleNotConfiguredException("Work scheduled not configured"));

        return WorkScheduleMapper.toGetWorkScheduleResponse(currentWorkSchedule);
    }

    // Helper method
    private void addWorkSchedule(ConfigureWorkScheduleRequest configureWorkScheduleRequest) {

        workScheduleRepository.save(WorkScheduleMapper.toWorkSchedule(configureWorkScheduleRequest));
    }

    private void updateWorkSchedule(WorkSchedule currentWorkSchedule, ConfigureWorkScheduleRequest configureWorkScheduleRequest) {

        if (workScheduleValidator.isWorkScheduleUnchanged(currentWorkSchedule, configureWorkScheduleRequest)) {

            return;
        }

        WorkScheduleMapper.updateWorkSchedule(currentWorkSchedule, configureWorkScheduleRequest);
    }
}
