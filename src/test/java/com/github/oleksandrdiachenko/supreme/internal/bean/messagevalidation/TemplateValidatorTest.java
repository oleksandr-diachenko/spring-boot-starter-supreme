package com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation;

import com.github.oleksandrdiachenko.supreme.internal.exception.IncorrectMessageTemplateException;
import com.github.oleksandrdiachenko.supreme.utils.template.TemplateParts;
import com.github.oleksandrdiachenko.supreme.utils.template.TemplateVariablesExtractor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TemplateValidatorTest {

    @InjectMocks
    private TemplateValidator templateValidator;

    @Mock
    private TemplateVariablesExtractor templateVariablesExtractor;

    @Test
    void shouldThrowExceptionWhenIndexIsGreaterThatParamsSize() throws NoSuchMethodException {
        when(templateVariablesExtractor.extract("Printing value: {1}"))
                .thenReturn(new TemplateParts(List.of(1), true));

        Method method = TestInstance.class.getDeclaredMethod("print", int.class);
        assertThatThrownBy(() -> templateValidator.checkMessage(method, "Printing value: {1}"))
                .isInstanceOf(IncorrectMessageTemplateException.class);
    }

    @Test
    void shouldThrowExceptionWhenIndexIsNegative() throws NoSuchMethodException {
        when(templateVariablesExtractor.extract("Printing value: {-1}"))
                .thenReturn(new TemplateParts(List.of(-1), true));

        Method method = TestInstance.class.getDeclaredMethod("print", int.class);
        assertThatThrownBy(() -> templateValidator.checkMessage(method, "Printing value: {-1}"))
                .isInstanceOf(IncorrectMessageTemplateException.class);
    }

    @Test
    void shouldThrowExceptionWhenReturnTypeIsVoidButMessageExpectIt() throws NoSuchMethodException {
        when(templateVariablesExtractor.extract("The result of printing is: {retVal}"))
                .thenReturn(new TemplateParts(List.of(-1), true));

        Method method = TestInstance.class.getDeclaredMethod("print", int.class);
        assertThatThrownBy(() -> templateValidator.checkMessage(method, "The result of printing is: {retVal}"))
                .isInstanceOf(IncorrectMessageTemplateException.class);
    }

    private static class TestInstance {

        public void print(int a) {
            // do something
        }
    }
}
