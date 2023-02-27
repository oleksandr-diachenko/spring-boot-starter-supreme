package com.epam.supreme.integration.testinstance.info;

import com.epam.supreme.annotation.LogInfo;
import com.epam.supreme.integration.testinstance.AbstractInstance;
import org.springframework.stereotype.Component;

@Component
public class InfoWithMessageInstance extends AbstractInstance {

    @LogInfo(message = PRINT_MESSAGE)
    @Override
    public void print(int a, int b) {
        super.print(a, b);
    }

    @LogInfo(message = EXCHANGE_RATE_MESSAGE)
    @Override
    public int getExchangeRate() {
        return super.getExchangeRate();
    }

    @LogInfo(message = SUM_MESSAGE)
    @Override
    public int sum(int a, int b) {
        return super.sum(a, b);
    }
}
