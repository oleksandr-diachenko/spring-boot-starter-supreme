package com.epam.supreme.aspect.template.defaults.resolver;

import jakarta.annotation.Nullable;
import org.aspectj.lang.JoinPoint;

public interface DefaultTemplateDynamicResolver {

    String resolve(String message, JoinPoint jp, @Nullable Object retVal);
}
