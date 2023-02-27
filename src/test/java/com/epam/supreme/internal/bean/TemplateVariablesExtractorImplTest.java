package com.epam.supreme.internal.bean;

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
        SupremeProperty property = new SupremeProperty(null, null, new SupremeProperty.Template("retVal", "{", "}"));
        extractor = new TemplateVariablesExtractorImpl(property);
    }

    @Test
    void shouldExtractVariablesWithRetVal() {
        TemplateSignature signature = extractor.extract("The sum of two numbers {a} and {b} is {retVal}");

        assertThat(signature).isEqualTo(new TemplateSignature(List.of("a", "b"), true));
    }

    @Test
    void shouldExtractVariablesWithoutRetVal() {
        TemplateSignature signature = extractor.extract("Values {a} and {b} were printed");

        assertThat(signature).isEqualTo(new TemplateSignature(List.of("a", "b"), false));
    }

    @Test
    void shouldExtractRetValWithoutVariables() {
        TemplateSignature signature = extractor.extract("Current exchange rate is {retVal}");

        assertThat(signature).isEqualTo(new TemplateSignature(emptyList(), true));
    }

    @Test
    void shouldReturnEmptySignatureWhenMethodDontHaveParamsAndReturnType() {
        TemplateSignature signature = extractor.extract("Process some data");

        assertThat(signature).isEqualTo(new TemplateSignature(emptyList(), false));
    }
}