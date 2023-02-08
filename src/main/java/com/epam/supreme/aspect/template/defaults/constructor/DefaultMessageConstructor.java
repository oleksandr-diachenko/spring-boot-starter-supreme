package com.epam.supreme.aspect.template.defaults.constructor;

import com.epam.supreme.aspect.template.defaults.provider.DefaultTemplateProvider;
import com.epam.supreme.aspect.template.defaults.resolver.DefaultTemplateDynamicResolver;
import com.epam.supreme.aspect.template.exception.DefaultTemplateProviderNotFound;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultMessageConstructor {

    private final List<DefaultTemplateProvider> defaultTemplateProviders;
    private final DefaultTemplateDynamicResolver defaultTemplateDynamicResolver;

    public String construct(JoinPoint jp, @Nullable Object retVal) {
        return defaultTemplateProviders.stream()
                .filter(resolver -> resolver.isApplicableFor(jp, retVal))
                .findFirst()
                .map(resolver -> resolver.retrieveDefaultTemplate(jp, retVal))
                .map(message -> defaultTemplateDynamicResolver.replace(message, jp, retVal))
                .orElseThrow(DefaultTemplateProviderNotFound::new);
    }
}
