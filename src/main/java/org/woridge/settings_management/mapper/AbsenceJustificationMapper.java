package org.woridge.settings_management.mapper;

import org.woridge.settings_management.dto.absence_justification.ConfigureAbsenceJustificationRequest;
import org.woridge.settings_management.dto.absence_justification.GetAbsenceJustificationResponse;
import org.woridge.settings_management.model.AbsenceJustification;

public class AbsenceJustificationMapper {

    public static AbsenceJustification toAbsenceJustification(ConfigureAbsenceJustificationRequest configureAbsenceJustificationRequest) {

        return AbsenceJustification
                .builder()
                .allowedDelayInHours(configureAbsenceJustificationRequest.allowedDelayInHours())
                .build();
    }

    public static void updateAbsenceJustification(AbsenceJustification currentAbsenceJustification, ConfigureAbsenceJustificationRequest configureAbsenceJustificationRequest) {

        currentAbsenceJustification.setAllowedDelayInHours(configureAbsenceJustificationRequest.allowedDelayInHours());
    }

    public static GetAbsenceJustificationResponse toGetAbsenceJustificationResponse(AbsenceJustification absenceJustification) {

        return new GetAbsenceJustificationResponse(
                absenceJustification.getId(),
                absenceJustification.getAllowedDelayInHours()
        );
    }
}
