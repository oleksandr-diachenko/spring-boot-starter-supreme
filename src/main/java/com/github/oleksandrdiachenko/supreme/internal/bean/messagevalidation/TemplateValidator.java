package com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation;

import com.github.oleksandrdiachenko.supreme.internal.exception.IncorrectMessageTemplateException;
import com.github.oleksandrdiachenko.supreme.utils.template.TemplateParts;
import com.github.oleksandrdiachenko.supreme.utils.template.TemplateVariablesExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
        checkMethodParameters(method, signature.paramsIndexes());
    }

    private void checkMethodParameters(Method method, List<Integer> indexes) {
        Parameter[] params = method.getParameters();
        for (int index : indexes) {
            if (index < 0 || index >= params.length) {
                throw new IncorrectMessageTemplateException("Method don't have parameter at index: " + index);
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
