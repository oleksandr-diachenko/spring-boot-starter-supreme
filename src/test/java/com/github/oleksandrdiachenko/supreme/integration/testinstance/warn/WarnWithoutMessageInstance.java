package com.github.oleksandrdiachenko.supreme.integration.testinstance.warn;

import com.github.oleksandrdiachenko.supreme.annotation.LogWarn;
import com.github.oleksandrdiachenko.supreme.integration.testinstance.AbstractInstance;
import org.springframework.stereotype.Component;

@Component
public class WarnWithoutMessageInstance extends AbstractInstance {

    @LogWarn
    @Override
    public void print(int a, int b) {
        super.print(a, b);
    }

    @LogWarn
    @Override
    public int getExchangeRate() {
        return super.getExchangeRate();
    }

    @LogWarn
    @Override
    public int sum(int a, int b) {
        return super.sum(a, b);
    }
}
