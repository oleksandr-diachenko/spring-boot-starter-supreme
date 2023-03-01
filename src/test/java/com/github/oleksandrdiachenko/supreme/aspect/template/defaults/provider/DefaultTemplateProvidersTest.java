package com.github.oleksandrdiachenko.supreme.aspect.template.defaults.provider;

import com.github.oleksandrdiachenko.supreme.aspect.template.exception.DefaultTemplateProviderNotFound;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultTemplateProvidersTest {

    private DefaultTemplateProviders defaultTemplateProviders;

    @Mock
    private DefaultTemplateProvider defaultTemplateProvider;
    @Mock
    private JoinPoint jp;

    @BeforeEach
    void setUp() {
        defaultTemplateProviders = new DefaultTemplateProviders(List.of(defaultTemplateProvider));
    }

    @Test
    void shouldRetrieveDefaultMessageTemplate() {
        int retVal = 3;
        when(defaultTemplateProvider.isApplicableFor(jp, retVal)).thenReturn(true);
        when(defaultTemplateProvider.retrieveDefaultTemplate(jp, retVal)).thenReturn("Returned valus is {retVal}");

        String template = defaultTemplateProviders.retrieveDefaultTemplate(jp, retVal);

        assertThat(template).isEqualTo("Returned valus is {retVal}");
    }

    @Test
    void shouldThrowExceptionWhenResponsibleProviderNotFound() {
        int retVal = 3;
        when(defaultTemplateProvider.isApplicableFor(jp, retVal)).thenReturn(false);

        assertThatThrownBy(() -> defaultTemplateProviders.retrieveDefaultTemplate(jp, retVal))
                .isInstanceOf(DefaultTemplateProviderNotFound.class);
    }
}