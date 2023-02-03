package com.epam.methodlog.property;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("method.log")
public class MethodLogProperty {

    private Level loggerLevel;
    private String packageToScan;
}