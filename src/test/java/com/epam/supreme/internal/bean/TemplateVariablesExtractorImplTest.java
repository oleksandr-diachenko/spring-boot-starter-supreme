package com.epam.supreme.internal.bean;

import com.epam.supreme.utils.template.TemplateParts;
import com.epam.supreme.utils.template.TemplateVariablesExtractor;
import com.epam.supreme.utils.template.TemplateVariablesExtractorImpl;
import com.epam.supreme.property.SupremeProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class TemplateVariablesExtractorImplTest {

    private TemplateVariablesExtractor extractor;

    @BeforeEach
    void setUp() {
        SupremeProperty property = new SupremeProperty(null, new SupremeProperty.Template("retVal", "{", "}"));
        extractor = new TemplateVariablesExtractorImpl(property);
    }

    @Test
    void shouldExtractVariablesWithRetVal() {
        TemplateParts signature = extractor.extract("The sum of two numbers {a} and {b} is {retVal}");

        assertThat(signature).isEqualTo(new TemplateParts(List.of("a", "b"), true));
    }

    @Test
    void shouldExtractVariablesWithoutRetVal() {
        TemplateParts signature = extractor.extract("Values {a} and {b} were printed");

        assertThat(signature).isEqualTo(new TemplateParts(List.of("a", "b"), false));
    }

    @Test
    void shouldExtractRetValWithoutVariables() {
        TemplateParts signature = extractor.extract("Current exchange rate is {retVal}");

        assertThat(signature).isEqualTo(new TemplateParts(emptyList(), true));
    }

    @Test
    void shouldReturnEmptySignatureWhenMethodDontHaveParamsAndReturnType() {
        TemplateParts signature = extractor.extract("Process some data");

        assertThat(signature).isEqualTo(new TemplateParts(emptyList(), false));
    }
}