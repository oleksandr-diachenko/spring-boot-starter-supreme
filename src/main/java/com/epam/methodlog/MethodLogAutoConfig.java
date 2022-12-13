package com.epam.methodlog;

import com.epam.methodlog.utils.formatter.MapWithoutBracketsStringFormatter;
import com.epam.methodlog.utils.formatter.StringFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    @ConditionalOnMissingBean(value = StringFormatter.class, name = "mapStringFormatter")
    public StringFormatter<Map<String, Object>> mapStringFormatter() {
        return new MapWithoutBracketsStringFormatter<>();
    }
}