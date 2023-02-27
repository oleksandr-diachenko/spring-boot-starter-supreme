package com.epam.supreme.internal.bean;

import com.epam.supreme.annotation.LogInfo;
import com.epam.supreme.internal.exception.IncorrectMessageTemplateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnnotationMessageValidatorBPPTest {

    @InjectMocks
    private AnnotationMessageValidatorBPP validator;

    @Mock
    private TemplateVariablesExtractor templateVariablesExtractor;


    @Test
    void shouldNotThrowExceptionWhenParametersAreCorrect() {
        when(templateVariablesExtractor.extract("{a} {b} {retVal}"))
                .thenReturn(new TemplateSignature(List.of("a", "b"), true));

        Bean bean = new Bean();
        validator.postProcessAfterInitialization(bean, "");
    }

    @Test
    void shouldThrowExceptionWhenVariableDoesntMatchParameterNames() {
        when(templateVariablesExtractor.extract("{c}"))
                .thenReturn(new TemplateSignature(List.of("c"), false));

        BeanWithIncorrectVariable bean = new BeanWithIncorrectVariable();
        assertThatThrownBy(() -> validator.postProcessAfterInitialization(bean, ""))
                .isInstanceOf(IncorrectMessageTemplateException.class)
                .hasMessage("Method don't have parameter: c");
    }

    @Test
    void shouldThrowExceptionWhenReturnTypeIsVoidButRequireInMessage() {
        when(templateVariablesExtractor.extract("{retVal}"))
                .thenReturn(new TemplateSignature(emptyList(), true));

        BeanWithoutReturnType bean = new BeanWithoutReturnType();
        assertThatThrownBy(() -> validator.postProcessAfterInitialization(bean, ""))
                .isInstanceOf(IncorrectMessageTemplateException.class)
                .hasMessage("The method has a void return value, but a message expects retVal");
    }

    private static class Bean {

        @LogInfo(message = "{a} {b} {retVal}")
        public int sum(int a, int b) {
            return a + b;
        }
    }

    private static class BeanWithIncorrectVariable {

        @LogInfo(message = "{c}")
        public void print(int a) {
            //do nothing;
        }
    }

    private static class BeanWithoutReturnType {

        @LogInfo(message = "{retVal}")
        public void print() {
            // do nothing;
        }
    }
}