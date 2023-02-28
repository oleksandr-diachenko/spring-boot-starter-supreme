package com.epam.supreme;

import com.epam.supreme.property.SupremeProperty;
import com.epam.supreme.utils.formatter.MapWithoutBracketsStringFormatter;
import com.epam.supreme.utils.formatter.StringFormatter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.epam.supreme")
@EnableConfigurationProperties(SupremeProperty.class)
@PropertySource("classpath:supreme-application.properties")
public class MethodLogAutoConfig {

    @Bean
    public StringFormatter<Map<String, Object>> mapStringFormatter() {
        return new MapWithoutBracketsStringFormatter<>();
    }
}