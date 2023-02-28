package com.github.oleksandrdiachenko.supreme.aspect;

import com.github.oleksandrdiachenko.supreme.annotation.LogWarn;
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
public class LogWarnAspect {

    private final LogPrinter logPrinter;
    private final MessageResolver<LogWarn> messageResolver;

    @Pointcut("@annotation(com.github.oleksandrdiachenko.supreme.annotation.LogWarn)")
    public void anyMethodAnnotatedWithLogWarn() {
        // pointcut
    }

    @AfterReturning(value = "anyMethodAnnotatedWithLogWarn()", returning = "retVal")
    public void logMethod(JoinPoint jp, @Nullable Object retVal) {
        String message = messageResolver.resolve(jp, retVal, LogWarn.class, LogWarn::message);
        logPrinter.log(jp, message, Logger::warn);
    }
}