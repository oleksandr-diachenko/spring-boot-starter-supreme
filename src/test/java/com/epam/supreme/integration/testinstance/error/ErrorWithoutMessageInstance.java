package com.epam.supreme.integration.testinstance.error;

import com.epam.supreme.annotation.LogError;
import com.epam.supreme.integration.testinstance.AbstractInstance;
import org.springframework.stereotype.Component;

@Component
public class ErrorWithoutMessageInstance extends AbstractInstance {

    @LogError
    @Override
    public void print(int a, int b) {
        super.print(a, b);
    }

    @LogError
    @Override
    public int getExchangeRate() {
        return super.getExchangeRate();
    }

    @LogError
    @Override
    public int sum(int a, int b) {
        return super.sum(a, b);
    }
}
