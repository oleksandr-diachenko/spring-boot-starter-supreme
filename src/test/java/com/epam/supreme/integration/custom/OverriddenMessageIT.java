package com.epam.supreme.integration.custom;

import com.epam.supreme.TestConfig;
import com.epam.supreme.integration.testinstance.info.InfoWithoutMessageInstance;
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

        assertThat(output).contains("Custom message from method: sum with parameters: a=1, b=2 and returned value as 3");
    }
}
