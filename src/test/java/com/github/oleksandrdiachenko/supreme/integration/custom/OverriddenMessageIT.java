package com.github.oleksandrdiachenko.supreme.integration.custom;

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

@SpringBootTest(classes = TestConfig.class, properties = "supreme.message.in-out=" +
        "Custom message from method: {method} with parameters: {params} and returned value as {retVal}"
)
@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
public class OverriddenMessageIT {

    @Autowired
    private InfoWithoutMessageInstance instance;

    @Test
    void shouldLogNumbersAndTheirSum(CapturedOutput output) {
        instance.sum(1, 2);

        assertThat(output).contains("Custom message from method: sum with parameters: 0=1, 1=2 and returned value as 3");
    }
}
