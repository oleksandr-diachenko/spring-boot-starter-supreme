package com.epam.supreme.internal.bean.messagevalidation;

import com.epam.supreme.annotation.LogInfo;
import com.epam.supreme.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class LogInfoTemplateValidator implements Validator<Method> {

    private final TemplateValidator templateValidator;

    @Override
    public void validate(Method method) {
        if (method.isAnnotationPresent(LogInfo.class)) {
            LogInfo annotation = method.getAnnotation(LogInfo.class);
            templateValidator.checkMessage(method, annotation.message());
        }
    }
}
