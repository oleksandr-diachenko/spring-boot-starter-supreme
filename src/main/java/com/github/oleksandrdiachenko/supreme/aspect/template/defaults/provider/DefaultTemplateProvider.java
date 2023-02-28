package com.github.oleksandrdiachenko.supreme.aspect.template.defaults.provider;

import jakarta.annotation.Nullable;
import org.aspectj.lang.JoinPoint;

public interface DefaultTemplateProvider {

    boolean isApplicableFor(JoinPoint jp, @Nullable Object retVal);

    String retrieveDefaultTemplate(JoinPoint jp, Object retVal);
}
