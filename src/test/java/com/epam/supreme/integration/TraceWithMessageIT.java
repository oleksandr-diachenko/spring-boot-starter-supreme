package com.epam.supreme.integration;

import com.epam.supreme.TestConfig;
import com.epam.supreme.integration.testinstance.TraceWithMessageInstance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestConfig.class, properties = "logging.level.com.epam.supreme.integration.testinstance=TRACE")
@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class TraceWithMessageIT {

    @Autowired
    private TraceWithMessageInstance instance;

    @Test
    void shouldLogPrintedValues(CapturedOutput output) {
        instance.print(1, 2);

        assertThat(output).contains("Values 1 and 2 were printed\n");
    }

    @Test
    void shouldLogExchangeRate(CapturedOutput output) {
        instance.getExchangeRate();

        assertThat(output).contains("Current exchange rate is 40\n");
    }

    @Test
    void shouldLogNumbersAndTheirSum(CapturedOutput output) {
        instance.sum(1, 2);

        assertThat(output).contains("The sum of two numbers 1 and 2 is 3\n");
    }
}