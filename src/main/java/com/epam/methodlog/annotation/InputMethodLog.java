package com.epam.methodlog.annotation;

import org.slf4j.event.Level;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.slf4j.event.Level.*;

@Target(METHOD)
@Retention(RUNTIME)
public @interface InputMethodLog {

    Level logLevel() default INFO;
}
