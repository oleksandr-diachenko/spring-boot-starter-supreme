package com.epam.supreme.integration;

import com.epam.supreme.TestConfig;
import com.epam.supreme.integration.testinstance.DebugWithoutMessageInstance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestConfig.class)
@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class DebugWithOutMessageIT {

    @Autowired
    private DebugWithoutMessageInstance instance;

    @Test
    void shouldLogPrintedValues(CapturedOutput output) {
        instance.print(1, 2);

        assertThat(output).contains("Method: 'print' was called with parameters: a=1, b=2\n");
    }

    @Test
    void shouldLogExchangeRate(CapturedOutput output) {
        instance.getExchangeRate();

        assertThat(output).contains("Method: 'getExchangeRate' returned: 40\n");
    }

    @Test
    void shouldLogNumbersAndTheirSum(CapturedOutput output) {
        instance.sum(1, 2);

        assertThat(output).contains("Method: 'sum' was called with parameters: a=1, b=2 and returned: 3\n");
    }
}