package com.epam.methodlog.aspect.log.level;

import com.epam.methodlog.aspect.lookup.AspectLoggerLookup;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;

@RequiredArgsConstructor
public class LoggerInfo implements Logger {

    private final AspectLoggerLookup aspectLoggerLookup;

    @Override
    public void log(JoinPoint jp, String message, Object... args) {
        org.slf4j.Logger logger = aspectLoggerLookup.lookup(jp);
        logger.info(message, args);
    }
}