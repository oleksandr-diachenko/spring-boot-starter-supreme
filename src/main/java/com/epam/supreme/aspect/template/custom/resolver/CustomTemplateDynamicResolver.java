package com.epam.supreme.aspect.template.custom.resolver;

import jakarta.annotation.Nullable;

import java.util.Map;

public interface CustomTemplateDynamicResolver {

    String resolve(String template, Map<String, Object> args, @Nullable Object retVal);
}
