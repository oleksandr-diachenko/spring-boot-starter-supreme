package com.epam.methodlog;

import com.epam.methodlog.aspect.log.level.Logger;
import com.epam.methodlog.aspect.log.level.LoggerDebug;
import com.epam.methodlog.aspect.log.level.LoggerInfo;
import com.epam.methodlog.aspect.lookup.AspectLoggerLookup;
import com.epam.methodlog.utils.formatter.MapWithoutBracketsStringFormatter;
import com.epam.methodlog.utils.formatter.StringFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class MethodLogAutoConfig {

    @Bean
    public StringFormatter<Map<String, Object>> mapStringFormatter() {
        return new MapWithoutBracketsStringFormatter<>();
    }

    @Bean
    @ConditionalOnProperty(name = "method.log.level", havingValue = "INFO")
    public Logger logInfo(AspectLoggerLookup aspectLoggerLookup) {
        return new LoggerInfo(aspectLoggerLookup);
    }

    @Bean
    @ConditionalOnProperty(name = "method.log.level", havingValue = "DEBUG")
    public Logger logDebug(AspectLoggerLookup aspectLoggerLookup) {
        return new LoggerDebug(aspectLoggerLookup);
    }
}