package com.epam.supreme.aspect.template.custom.resolver;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomCustomTemplateDynamicResolverImpl implements CustomTemplateDynamicResolver {

    @Override
    public String resolve(String template, Map<String, Object> args, @Nullable Object retVal) {
        if (retVal != null) {
            template = template.replace("{retVal}", retVal.toString());
        }
        for (Map.Entry<String, Object> entry : args.entrySet()) {
            template = template.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }
        return template;
    }
}
