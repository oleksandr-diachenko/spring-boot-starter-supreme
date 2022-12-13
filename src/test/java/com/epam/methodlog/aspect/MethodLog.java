package com.epam.methodlog.aspect;

import com.epam.methodlog.annotation.InputMethodLog;
import com.epam.methodlog.annotation.OutputMethodLog;
import org.springframework.stereotype.Component;

@Component
public class MethodLog {

    @OutputMethodLog
    public int sum(int a, int b) {
        return a + b;
    }


    @InputMethodLog
    public void print(int a, int b) {
        // print logic
    }
}