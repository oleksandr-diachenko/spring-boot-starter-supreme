package com.github.oleksandrdiachenko.supreme.internal.bean;

import com.github.oleksandrdiachenko.supreme.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AnnotationTemplateValidatorBPPTest {

    private BeanPostProcessor annotationMessageValidatorBPP;

    @Mock
    private Validator<Method> validator;

    @BeforeEach
    void setUp() {
        annotationMessageValidatorBPP = new AnnotationMessageValidatorBPP(List.of(validator));
    }

    @Test
    void shouldCallValidator() throws NoSuchMethodException {
        Object bean = new Object();
        annotationMessageValidatorBPP.postProcessAfterInitialization(bean, "");

        verify(validator).validate(bean.getClass().getDeclaredMethod("toString"));
    }
}