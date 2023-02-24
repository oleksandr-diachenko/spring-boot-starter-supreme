package com.epam.supreme.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("supreme")
public record SupremeProperty(String packageToScan, Message message) {

    public record Message(String in, String out, String inOut) {
    }
}