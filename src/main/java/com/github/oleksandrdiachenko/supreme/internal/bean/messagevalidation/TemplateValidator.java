package com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation;

import com.github.oleksandrdiachenko.supreme.internal.exception.IncorrectMessageTemplateException;
import com.github.oleksandrdiachenko.supreme.utils.template.TemplateParts;
import com.github.oleksandrdiachenko.supreme.utils.template.TemplateVariablesExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TemplateValidator {

    private final TemplateVariablesExtractor templateVariablesExtractor;

    public void checkMessage(Method method, String template) {
        if (template.isEmpty()) {
            return;
        }
        TemplateParts signature = templateVariablesExtractor.extract(template);
        checkReturnValue(method, signature.isRetValPresent());
        checkMethodParameters(method, signature.params());
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
