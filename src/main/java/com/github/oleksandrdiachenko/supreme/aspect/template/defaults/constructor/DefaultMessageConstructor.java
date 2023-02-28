package com.github.oleksandrdiachenko.supreme.aspect.template.defaults.constructor;

import com.github.oleksandrdiachenko.supreme.aspect.template.defaults.provider.DefaultTemplateProvider;
import com.github.oleksandrdiachenko.supreme.aspect.template.defaults.resolver.DefaultTemplateDynamicResolver;
import com.github.oleksandrdiachenko.supreme.aspect.template.exception.DefaultTemplateProviderNotFound;
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
                .filter(provider -> provider.isApplicableFor(jp, retVal))
                .findFirst()
                .map(provider -> provider.retrieveDefaultTemplate(jp, retVal))
                .map(message -> defaultTemplateDynamicResolver.resolve(message, jp, retVal))
                .orElseThrow(DefaultTemplateProviderNotFound::new);
    }
}
