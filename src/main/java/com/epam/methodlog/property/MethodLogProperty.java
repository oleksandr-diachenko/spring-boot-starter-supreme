package com.epam.methodlog.property;

import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "method.log")
public class MethodLogProperty {

    private Level logLevel;

    Level getLogLevel() {
        return logLevel;
    }

    void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }
}