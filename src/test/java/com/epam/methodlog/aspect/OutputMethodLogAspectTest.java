package com.epam.methodlog.aspect;

import com.epam.methodlog.TestConfig;
import com.epam.methodlog.tags.Spring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@Spring
@SpringBootTest(classes = TestConfig.class)
@ExtendWith(OutputCaptureExtension.class)
class OutputMethodLogAspectTest {

    @Autowired
    private MethodLog methodLog;

    @Test
    void shouldLogReturnedValue(CapturedOutput output) {
        int result = methodLog.sum(1, 2);

        assertThat(result).isEqualTo(3);
        assertThat(output)
                .contains("Method: 'sum' returned: 3");
    }
}