package com.github.oleksandrdiachenko.supreme.internal.bean;

import com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation.MethodValidators;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class AnnotationMessageValidatorBPP implements BeanPostProcessor {

    private final MethodValidators methodValidators;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            methodValidators.validate(method);
        }
        return bean;
    }
}
