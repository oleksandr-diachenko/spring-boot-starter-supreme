package com.epam.methodlog.aspect;

import com.epam.methodlog.annotation.InputMethodLog;
import com.epam.methodlog.annotation.OutputMethodLog;
import org.springframework.stereotype.Component;

import static org.slf4j.event.Level.DEBUG;

@Component
public class MethodLog {

    @OutputMethodLog
    @InputMethodLog
    public int info(int a, int b) {
        return a + b;
    }

    @OutputMethodLog(logLevel = DEBUG)
    @InputMethodLog(logLevel = DEBUG)
    public int debug(int a, int b) {
        return a + b;
    }
}