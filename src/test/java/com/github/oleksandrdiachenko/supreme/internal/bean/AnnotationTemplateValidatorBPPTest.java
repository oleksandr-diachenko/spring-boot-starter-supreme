package com.github.oleksandrdiachenko.supreme.internal.bean;

import com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation.MethodValidators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.config.BeanPostProcessor;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AnnotationTemplateValidatorBPPTest {

    private BeanPostProcessor annotationMessageValidatorBPP;

    @Mock
    private MethodValidators methodValidators;

    @BeforeEach
    void setUp() {
        annotationMessageValidatorBPP = new AnnotationMessageValidatorBPP(methodValidators);
    }

    @Test
    void shouldCallValidator() throws NoSuchMethodException {
        Object bean = new Object();
        annotationMessageValidatorBPP.postProcessAfterInitialization(bean, "");

        verify(methodValidators).validate(bean.getClass().getDeclaredMethod("toString"));
    }
}