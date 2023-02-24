package com.epam.supreme.aspect.template.defaults.constructor;

import com.epam.supreme.aspect.template.defaults.provider.DefaultTemplateProvider;
import com.epam.supreme.aspect.template.defaults.resolver.DefaultTemplateDynamicResolver;
import com.epam.supreme.aspect.template.exception.DefaultTemplateProviderNotFound;
import org.aspectj.lang.JoinPoint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultMessageConstructorTest {

    private DefaultMessageConstructor constructor;

    @Mock
    private DefaultTemplateProvider defaultTemplateProvider;
    @Mock
    private DefaultTemplateDynamicResolver defaultTemplateDynamicResolver;
    @Mock
    private JoinPoint jp;

    @BeforeEach
    void setUp() {
        constructor = new DefaultMessageConstructor(List.of(defaultTemplateProvider), defaultTemplateDynamicResolver);
    }

    @Test
    void shouldThrowExceptionWhenProvidersIsEmpty() {
        constructor = new DefaultMessageConstructor(emptyList(), defaultTemplateDynamicResolver);

        Object retVal = new Object();
        Assertions.assertThatThrownBy(() -> constructor.construct(jp, retVal))
                .isInstanceOf(DefaultTemplateProviderNotFound.class);
    }

    @Test
    void shouldThrowExceptionWhenCorrespondingProviderNotFound() {
        Object retVal = new Object();
        when(defaultTemplateProvider.isApplicableFor(jp, retVal)).thenReturn(false);

        Assertions.assertThatThrownBy(() -> constructor.construct(jp, retVal))
                .isInstanceOf(DefaultTemplateProviderNotFound.class);
    }
}