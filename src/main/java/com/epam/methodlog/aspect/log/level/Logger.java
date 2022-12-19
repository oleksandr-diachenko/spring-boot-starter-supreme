package com.epam.methodlog.aspect.log.level;

import org.aspectj.lang.JoinPoint;

public interface Logger {

    void log(JoinPoint jp, String message, Object... args);
}