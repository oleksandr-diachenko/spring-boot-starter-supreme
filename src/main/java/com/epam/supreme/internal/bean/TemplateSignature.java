package com.epam.supreme.internal.bean;

import java.util.List;

public record TemplateSignature(List<String> variables, boolean isRetValRequired) {
}
