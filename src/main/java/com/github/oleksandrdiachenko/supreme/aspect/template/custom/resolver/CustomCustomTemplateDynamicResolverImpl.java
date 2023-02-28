package com.github.oleksandrdiachenko.supreme.aspect.template.custom.resolver;

import com.github.oleksandrdiachenko.supreme.property.SupremeProperty;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomCustomTemplateDynamicResolverImpl implements CustomTemplateDynamicResolver {

    private final SupremeProperty property;

    @Override
    public String resolve(String template, Map<Integer, Object> args, @Nullable Object retVal) {
        if (retVal != null) {
            template = template.replace(getRetValTemplate(), retVal.toString());
        }
        for (Map.Entry<Integer, Object> entry : args.entrySet()) {
            template = template.replace(getTemplatePrefix() + entry.getKey() + getTemplateSuffix(), entry.getValue().toString());
        }
        return template;
    }

    private  String getTemplateSuffix() {
        return property.template().suffix();
    }

    private  String getTemplatePrefix() {
        return property.template().prefix();
    }

    private  String getRetValTemplate() {
        return getTemplatePrefix() + property.template().retVal() + getTemplateSuffix();

    }
}
