package com.epam.methodlog;

import com.epam.methodlog.property.MethodLogProperty;
import com.epam.methodlog.utils.formatter.MapWithoutBracketsStringFormatter;
import com.epam.methodlog.utils.formatter.StringFormatter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "${method.log.packageToScan}")
@EnableConfigurationProperties(MethodLogProperty.class)
public class MethodLogAutoConfig {

    @Bean
    public StringFormatter<Map<String, Object>> mapStringFormatter() {
        return new MapWithoutBracketsStringFormatter<>();
    }
}