package com.epam.supreme.aspect.template.defaults.provider;

import com.epam.supreme.aspect.helper.ParamsResolver;
import com.epam.supreme.property.SupremeProperty;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InOutDefaultTemplateProvider implements DefaultTemplateProvider {

    private final ParamsResolver paramsResolver;
    private final SupremeProperty property;

    @Override
    public boolean isApplicableFor(JoinPoint jp, @Nullable Object retVal) {
        return !paramsResolver.resolve(jp).isEmpty() && retVal != null;
    }

    @Override
    public String retrieveDefaultTemplate(JoinPoint jp, Object retVal) {
        return property.message().inOut();
    }
}
