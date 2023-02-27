package com.epam.supreme.integration.testinstance.trace;

import com.epam.supreme.annotation.LogTrace;
import com.epam.supreme.integration.testinstance.AbstractInstance;
import org.springframework.stereotype.Component;

@Component
public class TraceWithMessageInstance extends AbstractInstance {

    @LogTrace(message = PRINT_MESSAGE)
    @Override
    public void print(int a, int b) {
        super.print(a, b);
    }

    @LogTrace(message = EXCHANGE_RATE_MESSAGE)
    @Override
    public int getExchangeRate() {
        return super.getExchangeRate();
    }

    @LogTrace(message = SUM_MESSAGE)
    @Override
    public int sum(int a, int b) {
        return super.sum(a, b);
    }
}
