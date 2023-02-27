package com.epam.supreme.internal.bean;

import com.epam.supreme.annotation.*;
import com.epam.supreme.internal.exception.IncorrectMessageTemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class AnnotationMessageValidatorBPP implements BeanPostProcessor {

    private final TemplateVariablesExtractor templateVariablesExtractor;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(LogInfo.class)) {
                LogInfo annotation = method.getAnnotation(LogInfo.class);
                checkMessage(method, annotation.message());
            }
            if (method.isAnnotationPresent(LogDebug.class)) {
                LogInfo annotation = method.getAnnotation(LogInfo.class);
                checkMessage(method, annotation.message());
            }
            if (method.isAnnotationPresent(LogTrace.class)) {
                LogTrace annotation = method.getAnnotation(LogTrace.class);
                checkMessage(method, annotation.message());
            }
            if (method.isAnnotationPresent(LogWarn.class)) {
                LogWarn annotation = method.getAnnotation(LogWarn.class);
                checkMessage(method, annotation.message());
            }
            if (method.isAnnotationPresent(LogError.class)) {
                LogError annotation = method.getAnnotation(LogError.class);
                checkMessage(method, annotation.message());
            }
        }

        return bean;
    }

    private void checkMessage(Method method, String message) {
        if (message.isEmpty()) {
            return;
        }
        TemplateSignature signature = templateVariablesExtractor.extract(message);
        checkReturnValue(method, signature.isRetValRequired());
        checkMethodParameters(method, signature.variables());

    }

    private void checkMethodParameters(Method method, List<String> variables) {
        Parameter[] params = method.getParameters();
        for (String variable : variables) {
            boolean dontHasVariable = Arrays.stream(params).noneMatch(param -> param.getName().equalsIgnoreCase(variable));
            if (dontHasVariable) {
                throw new IncorrectMessageTemplateException("Method don't have parameter: " + variable);
            }
        }
    }

    private void checkReturnValue(Method method, boolean isRetValPresent) {
        Class<?> returnType = method.getReturnType();
        if ((returnType == void.class || returnType == Void.class) && isRetValPresent) {
            throw new IncorrectMessageTemplateException("The method has a void return value, but a message expects retVal");
        }
    }
}
