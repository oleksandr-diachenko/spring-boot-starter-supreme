package com.github.oleksandrdiachenko.supreme;

import com.github.oleksandrdiachenko.supreme.internal.bean.AnnotationMessageValidatorBPP;
import com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation.MethodValidators;
import com.github.oleksandrdiachenko.supreme.property.SupremeProperty;
import com.github.oleksandrdiachenko.supreme.utils.formatter.MapWithoutBracketsStringFormatter;
import com.github.oleksandrdiachenko.supreme.utils.formatter.StringFormatter;
import org.springframework.beans.factory.config.BeanPostProcessor;
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
    public StringFormatter<Map<Integer, Object>> mapStringFormatter() {
        return new MapWithoutBracketsStringFormatter<>();
    }

    @Bean
    public BeanPostProcessor annotationMessageValidatorBPP(MethodValidators methodValidators) {
        return new AnnotationMessageValidatorBPP(methodValidators);
    }

}