package com.epam.supreme.internal.bean.messagevalidation;

import com.epam.supreme.annotation.LogTrace;
import com.epam.supreme.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class LogTraceTemplateValidator implements Validator<Method> {
    
    private final TemplateValidator templateValidator;
    
    @Override
    public void validate(Method method) {
        if (method.isAnnotationPresent(LogTrace.class)) {
            LogTrace annotation = method.getAnnotation(LogTrace.class);
            templateValidator.checkMessage(method, annotation.message());
        }
    }
}
