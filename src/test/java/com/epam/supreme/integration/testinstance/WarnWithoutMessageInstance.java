package com.epam.supreme.integration.testinstance;

import com.epam.supreme.annotation.LogWarn;
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
