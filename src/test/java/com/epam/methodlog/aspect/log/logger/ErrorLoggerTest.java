package com.epam.methodlog.aspect.log.logger;

import com.epam.methodlog.tags.Junit;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.slf4j.event.Level.ERROR;
import static org.slf4j.event.Level.INFO;

@Junit
@ExtendWith(MockitoExtension.class)
class ErrorLoggerTest {

    private Logger logger;

    @Mock
    private JoinPoint joinPoint;
    @Mock
    private org.slf4j.Logger sl4jLogger;

    @BeforeEach
    void setUp() {
        logger = new ErrorLogger();
    }

    @Test
    void shouldBeApplicableForError() {
        boolean applicableFor = logger.isApplicableFor(ERROR);

        assertThat(applicableFor).isTrue();
    }

    @Test
    void shouldNotBeApplicableForInfo() {
        boolean applicableFor = logger.isApplicableFor(INFO);

        assertThat(applicableFor).isFalse();
    }

    @Test
    void shouldLogMessage() {
        MessageInfo messageInfo = new MessageInfo(joinPoint, sl4jLogger, ERROR, "Message");

        logger.log(messageInfo);

        verify(sl4jLogger).error("Message");
    }
}