package com.epam.supreme.internal.bean.messagevalidation;

import com.epam.supreme.annotation.LogWarn;
import com.epam.supreme.validator.Validator;
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
