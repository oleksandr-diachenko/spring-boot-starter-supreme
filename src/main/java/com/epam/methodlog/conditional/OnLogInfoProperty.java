package com.epam.methodlog.conditional;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import static org.springframework.context.annotation.ConfigurationCondition.ConfigurationPhase.PARSE_CONFIGURATION;

public class OnLogInfoProperty extends AllNestedConditions {

    public OnLogInfoProperty() {
        super(PARSE_CONFIGURATION);
    }

    @ConditionalOnProperty(name = "method.log.logger-level", havingValue = "INFO")
    static class InfoValueCondition{}

    @ConditionalOnProperty(name = "method.log.logger-level")
    static class EmptyValueCondition{}
}
