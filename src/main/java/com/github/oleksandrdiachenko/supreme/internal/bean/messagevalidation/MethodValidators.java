package com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation;

import com.github.oleksandrdiachenko.supreme.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MethodValidators {

    private final List<Validator<Method>> validators;

    public void validate(Method method) {
        validators.forEach(validator -> validator.validate(method));
    }
}
