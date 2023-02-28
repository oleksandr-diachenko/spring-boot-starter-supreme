package com.github.oleksandrdiachenko.supreme.integration.info;

import com.github.oleksandrdiachenko.supreme.TestConfig;
import com.github.oleksandrdiachenko.supreme.integration.testinstance.info.InfoWithoutMessageInstance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestConfig.class,
        properties = "logging.level.com.github.oleksandrdiachenko.supreme.integration.testinstance.info=INFO")
@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class InfoWithOutMessageIT {

    @Autowired
    private InfoWithoutMessageInstance instance;

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