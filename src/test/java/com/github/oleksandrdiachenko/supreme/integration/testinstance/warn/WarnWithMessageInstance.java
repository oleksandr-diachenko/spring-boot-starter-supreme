package com.github.oleksandrdiachenko.supreme.integration.testinstance.warn;

import com.github.oleksandrdiachenko.supreme.annotation.LogWarn;
import com.github.oleksandrdiachenko.supreme.integration.testinstance.AbstractInstance;
import org.springframework.stereotype.Component;

@Component
public class WarnWithMessageInstance extends AbstractInstance {

    @LogWarn(message = PRINT_MESSAGE)
    @Override
    public void print(int a, int b) {
        super.print(a, b);
    }

    @LogWarn(message = EXCHANGE_RATE_MESSAGE)
    @Override
    public int getExchangeRate() {
        return super.getExchangeRate();
    }

    @LogWarn(message = SUM_MESSAGE)
    @Override
    public int sum(int a, int b) {
        return super.sum(a, b);
    }
}
