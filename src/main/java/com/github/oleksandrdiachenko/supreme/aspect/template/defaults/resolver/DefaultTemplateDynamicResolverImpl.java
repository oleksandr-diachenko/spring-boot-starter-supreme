package com.github.oleksandrdiachenko.supreme.aspect.template.defaults.resolver;

import com.github.oleksandrdiachenko.supreme.aspect.helper.MethodResolver;
import com.github.oleksandrdiachenko.supreme.aspect.helper.ParamsResolver;
import com.github.oleksandrdiachenko.supreme.property.SupremeProperty;
import com.github.oleksandrdiachenko.supreme.utils.formatter.StringFormatter;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DefaultTemplateDynamicResolverImpl implements DefaultTemplateDynamicResolver {

    private final MethodResolver methodResolver;
    private final ParamsResolver paramsResolver;
    private final StringFormatter<Map<Integer, Object>> mapStringFormatter;
    private final SupremeProperty property;

    @Override
    public String resolve(String message, JoinPoint jp, @Nullable Object retVal) {
        Method method = methodResolver.resolve(jp);
        String methodName = method.getName();
        Map<Integer, Object> params = paramsResolver.resolve(jp);
        String formattedParams = mapStringFormatter.format(params);
        String returnedValue = retVal != null ? retVal.toString() : "";
        SupremeProperty.Template template = property.template();
        return message
                .replace(variable(template, template.method()), methodName)
                .replace(variable(template, template.params()), formattedParams)
                .replace(variable(template, template.retVal()), returnedValue);
    }

    private String variable(SupremeProperty.Template template, String variable) {
        return template.prefix() + variable + template.suffix();
    }
}
