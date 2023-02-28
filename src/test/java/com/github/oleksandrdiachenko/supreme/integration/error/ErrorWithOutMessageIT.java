package com.github.oleksandrdiachenko.supreme.integration.error;

import com.github.oleksandrdiachenko.supreme.TestConfig;
import com.github.oleksandrdiachenko.supreme.integration.testinstance.error.ErrorWithoutMessageInstance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestConfig.class, properties = "logging.level.com.github.oleksandrdiachenko.supreme.integration.testinstance.error=ERROR")
@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class ErrorWithOutMessageIT {

    @Autowired
    private ErrorWithoutMessageInstance instance;

    @Test
    void shouldLogPrintedValues(CapturedOutput output) {
        instance.print(1, 2);

        assertThat(output).contains("Method: 'print' was called with parameters: 0=1, 1=2");
    }

    @Test
    void shouldLogExchangeRate(CapturedOutput output) {
        instance.getExchangeRate();

        assertThat(output).contains("Method: 'getExchangeRate' returned: 40");
    }

    @Test
    void shouldLogNumbersAndTheirSum(CapturedOutput output) {
        instance.sum(1, 2);

        assertThat(output).contains("Method: 'sum' was called with parameters: 0=1, 1=2 and returned: 3");
    }
}