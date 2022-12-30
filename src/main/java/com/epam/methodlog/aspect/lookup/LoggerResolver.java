package com.epam.methodlog.aspect.lookup;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerResolver {

    public Logger resolve(JoinPoint jp) {
        Class<?> aClass = jp.getTarget().getClass();
        return LoggerFactory.getLogger(aClass);
    }
}