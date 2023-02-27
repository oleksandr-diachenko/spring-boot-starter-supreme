package com.epam.supreme.internal.bean;

import com.epam.supreme.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.List;

@RequiredArgsConstructor
public class AnnotationMessageValidatorBPP implements BeanPostProcessor {

    private final List<Validator<Method>> validators;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            validators.forEach(validation -> validation.validate(method));
        }
        return bean;
    }
}
