package com.github.oleksandrdiachenko.supreme.aspect.template.exception;

public class DefaultTemplateProviderNotFound extends RuntimeException {

    public DefaultTemplateProviderNotFound() {
        super("The default template provider is not found");
    }
}
