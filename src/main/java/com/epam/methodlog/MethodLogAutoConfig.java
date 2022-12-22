package com.epam.methodlog;

import com.epam.methodlog.aspect.log.level.Logger;
import com.epam.methodlog.aspect.log.level.LoggerDebug;
import com.epam.methodlog.aspect.log.level.LoggerInfo;
import com.epam.methodlog.aspect.lookup.AspectLoggerLookup;
import com.epam.methodlog.conditional.ConditionalOnLogInfoProperty;
import com.epam.methodlog.property.MethodLogProperty;
import com.epam.methodlog.utils.formatter.MapWithoutBracketsStringFormatter;
import com.epam.methodlog.utils.formatter.StringFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
@EnableConfigurationProperties(MethodLogProperty.class)
public class MethodLogAutoConfig {

    @Bean
    public StringFormatter<Map<String, Object>> mapStringFormatter() {
        return new MapWithoutBracketsStringFormatter<>();
    }

    @Bean
    @Conditional(ConditionalOnLogInfoProperty.class)
    public Logger logInfo(AspectLoggerLookup aspectLoggerLookup) {
        return new LoggerInfo(aspectLoggerLookup);
    }

    @Bean
    @ConditionalOnProperty(name = "method.log.logger-level", havingValue = "DEBUG")
    public Logger logDebug(AspectLoggerLookup aspectLoggerLookup) {
        return new LoggerDebug(aspectLoggerLookup);
    }
}