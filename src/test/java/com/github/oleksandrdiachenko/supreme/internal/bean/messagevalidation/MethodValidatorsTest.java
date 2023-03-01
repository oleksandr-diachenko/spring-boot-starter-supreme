package com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation;

import com.github.oleksandrdiachenko.supreme.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MethodValidatorsTest {

    private MethodValidators methodValidators;

    @Mock
    private Validator<Method> validator;

    @BeforeEach
    void setUp() {
        methodValidators = new MethodValidators(List.of(validator));
    }

    @Test
    void shouldCallValidator() throws NoSuchMethodException {
        Object bean = new Object();
        Method method = bean.getClass().getDeclaredMethod("toString");

        methodValidators.validate(method);

        verify(validator).validate(method);
    }

}