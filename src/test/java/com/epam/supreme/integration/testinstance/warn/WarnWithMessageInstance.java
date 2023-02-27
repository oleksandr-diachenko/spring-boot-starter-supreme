package com.epam.supreme.integration.testinstance.warn;

import com.epam.supreme.annotation.LogWarn;
import com.epam.supreme.integration.testinstance.AbstractInstance;
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
