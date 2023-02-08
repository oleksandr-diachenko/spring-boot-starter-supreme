package com.epam.supreme.integration.testinstance;

import com.epam.supreme.annotation.LogDebug;
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
