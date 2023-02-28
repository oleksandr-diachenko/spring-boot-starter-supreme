package com.github.oleksandrdiachenko.supreme.aspect.template.defaults.resolver;

import com.github.oleksandrdiachenko.supreme.aspect.helper.MethodResolver;
import com.github.oleksandrdiachenko.supreme.aspect.helper.ParamsResolver;
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
    private final StringFormatter<Map<String, Object>> mapStringFormatter;

    @Override
    public String resolve(String message, JoinPoint jp, @Nullable Object retVal) {
        Method method = methodResolver.resolve(jp);
        String methodName = method.getName();
        Map<String, Object> params = paramsResolver.resolve(jp);
        String formattedParams = mapStringFormatter.format(params);
        String returnedValue = retVal != null ? retVal.toString() : "";
        return message
                .replace("{method}", methodName)
                .replace("{params}", formattedParams)
                .replace("{retVal}", returnedValue);
    }
}
