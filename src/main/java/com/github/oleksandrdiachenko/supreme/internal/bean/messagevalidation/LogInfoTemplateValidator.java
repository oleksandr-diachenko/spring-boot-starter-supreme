package com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation;

import com.github.oleksandrdiachenko.supreme.annotation.LogInfo;
import com.github.oleksandrdiachenko.supreme.validator.Validator;
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
