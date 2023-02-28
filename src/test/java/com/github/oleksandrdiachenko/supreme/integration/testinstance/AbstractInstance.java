package com.github.oleksandrdiachenko.supreme.integration.testinstance;

public abstract class AbstractInstance {

    protected final String PRINT_MESSAGE = "Values {0} and {1} were printed";
    protected final String EXCHANGE_RATE_MESSAGE = "Current exchange rate is {retVal}";
    protected final String SUM_MESSAGE = "The sum of two numbers {0} and {1} is {retVal}";

    public void print(int a, int b) {
        // do nothing
    }

    public int getExchangeRate() {
        return 40;
    }

    public int sum(int a, int b) {
        return a + b;
    }
}
