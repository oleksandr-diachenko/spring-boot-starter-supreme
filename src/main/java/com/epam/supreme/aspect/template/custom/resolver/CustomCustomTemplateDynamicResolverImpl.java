package com.epam.supreme.aspect.template.custom.resolver;

import com.epam.supreme.property.SupremeProperty;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomCustomTemplateDynamicResolverImpl implements CustomTemplateDynamicResolver {

    private final SupremeProperty property;

    @Override
    public String resolve(String template, Map<String, Object> args, @Nullable Object retVal) {
        if (retVal != null) {
            template = template.replace(getRetValTemplate(), retVal.toString());
        }
        for (Map.Entry<String, Object> entry : args.entrySet()) {
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
