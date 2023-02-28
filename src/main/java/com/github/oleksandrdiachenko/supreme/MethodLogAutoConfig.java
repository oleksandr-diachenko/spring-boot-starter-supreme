package com.github.oleksandrdiachenko.supreme;

import com.github.oleksandrdiachenko.supreme.property.SupremeProperty;
import com.github.oleksandrdiachenko.supreme.utils.formatter.MapWithoutBracketsStringFormatter;
import com.github.oleksandrdiachenko.supreme.utils.formatter.StringFormatter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.github.oleksandrdiachenko.supreme")
@EnableConfigurationProperties(SupremeProperty.class)
@PropertySource("classpath:supreme-application.properties")
public class MethodLogAutoConfig {

    @Bean
    public StringFormatter<Map<String, Object>> mapStringFormatter() {
        return new MapWithoutBracketsStringFormatter<>();
    }

}