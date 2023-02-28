package com.github.oleksandrdiachenko.supreme.internal.bean;

import com.github.oleksandrdiachenko.supreme.property.SupremeProperty;
import com.github.oleksandrdiachenko.supreme.utils.template.TemplateParts;
import com.github.oleksandrdiachenko.supreme.utils.template.TemplateVariablesExtractor;
import com.github.oleksandrdiachenko.supreme.utils.template.TemplateVariablesExtractorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class TemplateVariablesExtractorImplTest {

    private TemplateVariablesExtractor extractor;

    @BeforeEach
    void setUp() {
        SupremeProperty.Template template = new SupremeProperty.Template("retVal", "{", "}", "", "");
        SupremeProperty property = new SupremeProperty(null, template);
        extractor = new TemplateVariablesExtractorImpl(property);
    }

    @Test
    void shouldExtractVariablesWithRetVal() {
        TemplateParts signature = extractor.extract("The sum of two numbers {0} and {1} is {retVal}");

        assertThat(signature).isEqualTo(new TemplateParts(List.of(0, 1), true));
    }

    @Test
    void shouldExtractVariablesWithoutRetVal() {
        TemplateParts signature = extractor.extract("Values {0} and {1} were printed");

        assertThat(signature).isEqualTo(new TemplateParts(List.of(0, 1), false));
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