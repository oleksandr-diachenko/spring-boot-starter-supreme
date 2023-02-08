package com.epam.supreme;

import com.epam.supreme.property.SupremeProperty;
import com.epam.supreme.utils.formatter.MapWithoutBracketsStringFormatter;
import com.epam.supreme.utils.formatter.StringFormatter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.epam")
@EnableConfigurationProperties(SupremeProperty.class)
public class MethodLogAutoConfig {

    @Bean
    public StringFormatter<Map<String, Object>> mapStringFormatter() {
        return new MapWithoutBracketsStringFormatter<>();
    }
}