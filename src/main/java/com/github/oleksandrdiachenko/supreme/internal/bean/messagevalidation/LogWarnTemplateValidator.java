package com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation;

import com.github.oleksandrdiachenko.supreme.annotation.LogWarn;
import com.github.oleksandrdiachenko.supreme.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class LogWarnTemplateValidator implements Validator<Method> {
    
    private final TemplateValidator templateValidator;
    
    @Override
    public void validate(Method method) {
        if (method.isAnnotationPresent(LogWarn.class)) {
            LogWarn annotation = method.getAnnotation(LogWarn.class);
            templateValidator.checkMessage(method, annotation.message());
        }
    }
}
