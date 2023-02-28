package com.github.oleksandrdiachenko.supreme.utils.template;

import com.github.oleksandrdiachenko.supreme.property.SupremeProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.substringBetween;

@Component
@RequiredArgsConstructor
public class TemplateVariablesExtractorImpl implements TemplateVariablesExtractor {

    private final SupremeProperty property;

    @Override
    public TemplateParts extract(String message) {
        List<Integer> variableNames = new ArrayList<>();
        boolean isRetValRequired = false;
        for (String word : message.split(" ")) {
            if (isVariableTemplate(word)) {
                String variable = extractVariable(word);
                if (variable.equalsIgnoreCase(property.template().retVal())) {
                    isRetValRequired = true;
                } else {
                    variableNames.add(Integer.parseInt(variable));
                }
            }
        }
        return new TemplateParts(variableNames, isRetValRequired);
    }

    private String extractVariable(String word) {
        return substringBetween(word, property.template().prefix(), property.template().suffix());
    }

    private boolean isVariableTemplate(String word) {
        return word.startsWith(property.template().prefix()) && word.endsWith(property.template().suffix());
    }
}
