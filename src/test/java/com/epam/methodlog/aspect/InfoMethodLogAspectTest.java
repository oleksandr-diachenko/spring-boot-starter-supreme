package com.epam.methodlog.aspect;

import com.epam.methodlog.TestConfig;
import com.epam.methodlog.tags.Spring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@Spring
@SpringBootTest(classes = TestConfig.class)
@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class InfoMethodLogAspectTest {

    @Autowired
    private MethodLog methodLog;

    @Test
    void shouldLogInputParameters(CapturedOutput output) {
        int result = methodLog.info(1, 2);

        assertThat(result).isEqualTo(3);
        assertThat(output)
                .contains("Method: 'info' was called with parameters: a=1, b=2")
                .contains("Method: 'info' returned: 3");
    }
}