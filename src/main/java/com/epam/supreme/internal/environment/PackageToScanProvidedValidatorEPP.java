package com.epam.supreme.internal.environment;

import com.epam.supreme.internal.exception.EnvironmentPropertyNotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class PackageToScanProvidedValidatorEPP implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        if (environment.getProperty("supreme.package-to-scan") == null) {
            throw new EnvironmentPropertyNotFoundException("supreme.package-to-scan");
        }
    }
}