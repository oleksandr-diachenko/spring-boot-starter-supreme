package com.github.oleksandrdiachenko.supreme.aspect.template.defaults.provider;

import com.github.oleksandrdiachenko.supreme.aspect.template.exception.DefaultTemplateProviderNotFound;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultTemplateProviders {

    private final List<DefaultTemplateProvider> defaultTemplateProviderList;

    public String retrieveDefaultTemplate(JoinPoint jp, @Nullable Object retVal) {
        return defaultTemplateProviderList.stream()
                .filter(provider -> provider.isApplicableFor(jp, retVal))
                .findFirst()
                .map(provider -> provider.retrieveDefaultTemplate(jp, retVal))
                .orElseThrow(DefaultTemplateProviderNotFound::new);
    }
}
