package org.woridge.settings_management.service.implemetation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.woridge.settings_management.dto.absence_justification.ConfigureAbsenceJustificationRequest;
import org.woridge.settings_management.dto.absence_justification.GetAbsenceJustificationResponse;
import org.woridge.settings_management.exception.absence_justification.AbsenceJustificationNotConfiguredException;
import org.woridge.settings_management.mapper.AbsenceJustificationMapper;
import org.woridge.settings_management.model.AbsenceJustification;
import org.woridge.settings_management.repository.AbsenceJustificationRepository;
import org.woridge.settings_management.service.AbsenceJustificationService;
import org.woridge.settings_management.service.AbsenceJustificationValidator;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AbsenceJustificationServiceImpl implements AbsenceJustificationService {

    private final AbsenceJustificationRepository absenceJustificationRepository;
    private final AbsenceJustificationValidator absenceJustificationValidator;

    @Override
    public void configureAbsenceJustification(ConfigureAbsenceJustificationRequest configureAbsenceJustificationRequest) {

        Optional<AbsenceJustification> currentAbsenceJustification = absenceJustificationRepository.findFirstBy();

        currentAbsenceJustification.ifPresentOrElse(
                absenceJustification -> updateAbsenceJustification(absenceJustification, configureAbsenceJustificationRequest),
                () -> addAbsenceJustification(configureAbsenceJustificationRequest)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public GetAbsenceJustificationResponse getAbsenceJustification() {

        AbsenceJustification currentAbsenceJustification = absenceJustificationRepository.findFirstBy().orElseThrow(() ->
                new AbsenceJustificationNotConfiguredException("Absence justification not configured"));

        return AbsenceJustificationMapper.toGetAbsenceJustificationResponse(currentAbsenceJustification);
    }

    // Helper method
    private void addAbsenceJustification(ConfigureAbsenceJustificationRequest configureAbsenceJustificationRequest) {

        absenceJustificationRepository.save(AbsenceJustificationMapper.toAbsenceJustification(configureAbsenceJustificationRequest));
    }

    private void updateAbsenceJustification(AbsenceJustification currentAbsenceJustification, ConfigureAbsenceJustificationRequest configureAbsenceJustificationRequest) {

        if (absenceJustificationValidator.isAbsenceJustificationUnchanged(currentAbsenceJustification, configureAbsenceJustificationRequest)) {

            return;
        }

        AbsenceJustificationMapper.updateAbsenceJustification(currentAbsenceJustification, configureAbsenceJustificationRequest);
    }
}
