package com.github.oleksandrdiachenko.supreme.integration.testinstance.debug;

import com.github.oleksandrdiachenko.supreme.annotation.LogDebug;
import com.github.oleksandrdiachenko.supreme.integration.testinstance.AbstractInstance;
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
