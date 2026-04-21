package org.woridge.settings_management.service.implemetation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.woridge.settings_management.dto.annual_leave.ConfigureAnnualBalanceRequest;
import org.woridge.settings_management.dto.annual_leave.GetAnnualBalanceResponse;
import org.woridge.settings_management.exception.annual_leave.AnnualBalanceNotFoundException;
import org.woridge.settings_management.mapper.AnnualLeaveMapper;
import org.woridge.settings_management.model.AnnualLeave;
import org.woridge.settings_management.repository.AnnualLeaveRepository;
import org.woridge.settings_management.service.AnnualLeaveService;
import org.woridge.settings_management.service.AnnualLeaveValidator;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AnnualLeaveServiceImpl implements AnnualLeaveService {

    private final AnnualLeaveRepository annualLeaveRepository;
    private final AnnualLeaveValidator annualLeaveValidator;

    @Override
    public void configureAnnualBalance(ConfigureAnnualBalanceRequest configureAnnualBalanceRequest) {

        Optional<AnnualLeave> currentAnnualLeave = annualLeaveRepository.findFirstBy();

        currentAnnualLeave.ifPresentOrElse(
                annualLeave -> updateAnnualLeave(annualLeave, configureAnnualBalanceRequest),
                () -> addAnnualLeave(configureAnnualBalanceRequest)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public GetAnnualBalanceResponse getAnnualBalance() {

        AnnualLeave annualLeave = annualLeaveRepository.findFirstBy().orElseThrow(() ->
                new AnnualBalanceNotFoundException("Annual balance not found"));

        return AnnualLeaveMapper.toGetAnnualBalanceResponse(annualLeave);
    }

    // Helper method
    private void addAnnualLeave(ConfigureAnnualBalanceRequest configureAnnualBalanceRequest) {

        annualLeaveRepository.save(AnnualLeaveMapper.toAnnualLeave(configureAnnualBalanceRequest));
    }

    private void updateAnnualLeave(AnnualLeave currentAnnualLeave, ConfigureAnnualBalanceRequest configureAnnualBalanceRequest) {

        if (annualLeaveValidator.isAnnualLeaveUnchanged(currentAnnualLeave, configureAnnualBalanceRequest)) {

            return;
        }

        AnnualLeaveMapper.updateAnnualLeave(currentAnnualLeave, configureAnnualBalanceRequest);
    }
}
