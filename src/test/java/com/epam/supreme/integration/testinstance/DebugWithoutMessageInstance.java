package com.epam.supreme.integration.testinstance;

import com.epam.supreme.annotation.LogDebug;
import org.springframework.stereotype.Component;

@Component
public class DebugWithoutMessageInstance extends AbstractInstance {

    @LogDebug
    @Override
    public void print(int a, int b) {
        super.print(a, b);
    }

    @LogDebug
    @Override
    public int getExchangeRate() {
        return super.getExchangeRate();
    }

    @LogDebug
    @Override
    public int sum(int a, int b) {
        return super.sum(a, b);
    }
}
