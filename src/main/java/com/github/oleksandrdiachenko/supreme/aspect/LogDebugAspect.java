package com.github.oleksandrdiachenko.supreme.aspect;

import com.github.oleksandrdiachenko.supreme.annotation.LogDebug;
import com.github.oleksandrdiachenko.supreme.aspect.printer.LogPrinter;
import com.github.oleksandrdiachenko.supreme.aspect.template.MessageResolver;
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
public class LogDebugAspect {

    private final LogPrinter logPrinter;
    private final MessageResolver<LogDebug> messageResolver;

    @Pointcut("@annotation(com.github.oleksandrdiachenko.supreme.annotation.LogDebug)")
    public void anyMethodAnnotatedWithLogDebug() {
        // pointcut
    }

    @AfterReturning(value = "anyMethodAnnotatedWithLogDebug()", returning = "retVal")
    public void logMethod(JoinPoint jp, @Nullable Object retVal) {
        String message = messageResolver.resolve(jp, retVal, LogDebug.class, LogDebug::message);
        logPrinter.log(jp, message, Logger::debug);
    }
}