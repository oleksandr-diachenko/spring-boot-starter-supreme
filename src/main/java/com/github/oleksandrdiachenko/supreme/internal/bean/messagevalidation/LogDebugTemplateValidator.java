package com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation;

import com.github.oleksandrdiachenko.supreme.annotation.LogDebug;
import com.github.oleksandrdiachenko.supreme.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class LogDebugTemplateValidator implements Validator<Method> {
    
    private final TemplateValidator templateValidator;
    
    @Override
    public void validate(Method method) {
        if (method.isAnnotationPresent(LogDebug.class)) {
            LogDebug annotation = method.getAnnotation(LogDebug.class);
            templateValidator.checkMessage(method, annotation.message());
        }
    }
}
