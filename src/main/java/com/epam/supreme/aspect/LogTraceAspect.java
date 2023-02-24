package com.epam.supreme.aspect;

import com.epam.supreme.annotation.LogTrace;
import com.epam.supreme.aspect.printer.LogPrinter;
import com.epam.supreme.aspect.template.MessageResolver;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LogTraceAspect {

    private final LogPrinter logPrinter;
    private final MessageResolver<LogTrace> messageResolver;

    @Pointcut("@annotation(com.epam.supreme.annotation.LogTrace)")
    public void anyMethodAnnotatedWithLogTrace() {
        // pointcut
    }

    @AfterReturning(value = "anyMethodAnnotatedWithLogTrace()", returning = "retVal")
    public void logMethod(JoinPoint jp, @Nullable Object retVal) {
        String message = messageResolver.resolve(jp, retVal, LogTrace.class, LogTrace::message);
        logPrinter.log(jp, message, Logger::trace);
    }
}