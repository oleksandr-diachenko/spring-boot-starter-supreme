package com.epam.methodlog.aspect.lookup;

import com.epam.methodlog.tags.Junit;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Junit
@ExtendWith(MockitoExtension.class)
class LoggerResolverTest {

    @InjectMocks
    private LoggerResolver aspectLoggerResolver;

    @Mock
    private JoinPoint joinPoint;

    @Test
    void shouldReturnLogger() {
        when(joinPoint.getTarget()).thenReturn(new Target());

        Logger logger = aspectLoggerResolver.resolve(joinPoint);

        Logger expected = LoggerFactory.getLogger(Target.class);
        assertThat(logger).isSameAs(expected);
    }

    private static class Target {}
}