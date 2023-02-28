package com.github.oleksandrdiachenko.supreme.integration.testinstance.error;

import com.github.oleksandrdiachenko.supreme.annotation.LogError;
import com.github.oleksandrdiachenko.supreme.integration.testinstance.AbstractInstance;
import org.springframework.stereotype.Component;

@Component
public class ErrorWithMessageInstance extends AbstractInstance {

    @LogError(message = PRINT_MESSAGE)
    @Override
    public void print(int a, int b) {
        super.print(a, b);
    }

    @LogError(message = EXCHANGE_RATE_MESSAGE)
    @Override
    public int getExchangeRate() {
        return super.getExchangeRate();
    }

    @LogError(message = SUM_MESSAGE)
    @Override
    public int sum(int a, int b) {
        return super.sum(a, b);
    }
}
