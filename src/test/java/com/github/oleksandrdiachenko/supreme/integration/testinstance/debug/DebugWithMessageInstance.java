package com.github.oleksandrdiachenko.supreme.integration.testinstance.debug;

import com.github.oleksandrdiachenko.supreme.annotation.LogDebug;
import com.github.oleksandrdiachenko.supreme.integration.testinstance.AbstractInstance;
import org.springframework.stereotype.Component;

@Component
public class DebugWithMessageInstance extends AbstractInstance {

    @LogDebug(message = PRINT_MESSAGE)
    @Override
    public void print(int a, int b) {
        super.print(a, b);
    }

    @LogDebug(message = EXCHANGE_RATE_MESSAGE)
    @Override
    public int getExchangeRate() {
        return super.getExchangeRate();
    }

    @LogDebug(message = SUM_MESSAGE)
    @Override
    public int sum(int a, int b) {
        return super.sum(a, b);
    }
}
