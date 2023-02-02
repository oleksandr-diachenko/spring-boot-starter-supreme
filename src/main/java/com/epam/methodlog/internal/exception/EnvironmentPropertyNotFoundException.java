package com.epam.methodlog.internal.exception;

import static java.lang.String.*;

public class EnvironmentPropertyNotFoundException extends RuntimeException {

    public EnvironmentPropertyNotFoundException(String propertyName) {
        super(format("Property: '%s' not found. Please provide it via " +
                "application.properties/application.yml/command line", propertyName));
    }
}