package com.epam.supreme.aspect.template.custom.constructor;

import com.epam.supreme.aspect.helper.ParamsResolver;
import com.epam.supreme.aspect.template.custom.resolver.CustomTemplateDynamicResolver;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomMessageConstructor {

    private final CustomTemplateDynamicResolver customTemplateDynamicResolver;
    private final ParamsResolver paramsResolver;

    public String construct(JoinPoint jp, String template, @Nullable Object retVal) {
        Map<String, Object> params = paramsResolver.resolve(jp);
        return customTemplateDynamicResolver.resolve(template, params, retVal);
    }
}
