package org.woridge.settings_management.service.implemetation;

import org.springframework.stereotype.Service;
import org.woridge.settings_management.dto.absence_justification.ConfigureAbsenceJustificationRequest;
import org.woridge.settings_management.model.AbsenceJustification;
import org.woridge.settings_management.service.AbsenceJustificationValidator;

@Service
public class AbsenceJustificationValidatorImpl implements AbsenceJustificationValidator {

    @Override
    public boolean isAbsenceJustificationUnchanged(AbsenceJustification currentAbsenceJustification, ConfigureAbsenceJustificationRequest configureAbsenceJustificationRequest) {

        return currentAbsenceJustification.getAllowedDelayInHours() == configureAbsenceJustificationRequest.allowedDelayInHours();
    }
}
