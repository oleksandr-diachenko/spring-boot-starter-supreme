package com.github.oleksandrdiachenko.supreme.aspect.template.defaults.constructor;

import com.github.oleksandrdiachenko.supreme.aspect.template.defaults.provider.DefaultTemplateProviders;
import com.github.oleksandrdiachenko.supreme.aspect.template.defaults.resolver.DefaultTemplateDynamicResolver;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultMessageConstructor {

    private final DefaultTemplateProviders defaultTemplateProviders;
    private final DefaultTemplateDynamicResolver defaultTemplateDynamicResolver;

    public String construct(JoinPoint jp, @Nullable Object retVal) {
        String defaultTemplate = defaultTemplateProviders.retrieveDefaultTemplate(jp, retVal);
        return defaultTemplateDynamicResolver.resolve(defaultTemplate, jp, retVal);
    }
}
