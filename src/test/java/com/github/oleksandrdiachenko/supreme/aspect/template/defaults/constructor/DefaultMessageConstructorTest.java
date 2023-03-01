package com.github.oleksandrdiachenko.supreme.aspect.template.defaults.constructor;

import com.github.oleksandrdiachenko.supreme.aspect.template.defaults.provider.DefaultTemplateProviders;
import com.github.oleksandrdiachenko.supreme.aspect.template.defaults.resolver.DefaultTemplateDynamicResolver;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultMessageConstructorTest {

    private DefaultMessageConstructor constructor;

    @Mock
    private DefaultTemplateProviders defaultTemplateProviders;
    @Mock
    private DefaultTemplateDynamicResolver defaultTemplateDynamicResolver;
    @Mock
    private JoinPoint jp;

    @BeforeEach
    void setUp() {
        constructor = new DefaultMessageConstructor(defaultTemplateProviders, defaultTemplateDynamicResolver);
    }

    @Test
    void shouldThrowExceptionWhenCorrespondingProviderNotFound() {
        Object retVal = new Object();
        when(defaultTemplateProviders.retrieveDefaultTemplate(jp, retVal)).thenReturn("Message with variable {0}");
        when(defaultTemplateDynamicResolver.resolve("Message with variable {0}", jp, retVal))
                .thenReturn("Message with variable 3");

        String message = constructor.construct(jp, retVal);

        assertThat(message).isEqualTo("Message with variable 3");


    }
}