package com.github.oleksandrdiachenko.supreme.property;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("supreme")
@Validated
public record SupremeProperty(Message message, Template template) {

    public record Message(@NotBlank String in, @NotBlank String out, @NotBlank String inOut) {
    }

    public record Template(@NotBlank String retVal, @NotBlank String prefix, @NotBlank String suffix) {
    }
}