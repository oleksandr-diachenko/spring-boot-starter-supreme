package com.github.oleksandrdiachenko.supreme.integration.testinstance.info;

import com.github.oleksandrdiachenko.supreme.annotation.LogInfo;
import com.github.oleksandrdiachenko.supreme.integration.testinstance.AbstractInstance;
import org.springframework.stereotype.Component;

@Component
public class InfoWithoutMessageInstance extends AbstractInstance {

    @LogInfo
    @Override
    public void print(int a, int b) {
        super.print(a, b);
    }

    @LogInfo
    @Override
    public int getExchangeRate() {
        return super.getExchangeRate();
    }

    @LogInfo
    @Override
    public int sum(int a, int b) {
        return super.sum(a, b);
    }
}
