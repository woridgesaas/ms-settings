package org.woridge.settings_management.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("work-schedule")
@Getter
@Setter
public class WorkScheduleProperties {

    private double MIN_DAILY_HOURS;
    private double MAX_DAILY_HOURS;
}
