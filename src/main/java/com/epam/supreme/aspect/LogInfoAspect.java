package com.epam.supreme.aspect;

import com.epam.supreme.annotation.LogInfo;
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
public class LogInfoAspect {

    private final LogPrinter logPrinter;
    private final MessageResolver<LogInfo> messageResolver;

    @Pointcut("@annotation(com.epam.supreme.annotation.LogInfo)")
    public void anyMethodAnnotatedWithLogInfo() {
        // pointcut
    }

    @AfterReturning(value = "anyMethodAnnotatedWithLogInfo()", returning = "retVal")
    public void logMethod(JoinPoint jp, @Nullable Object retVal) {
        String message = messageResolver.resolve(jp, retVal, LogInfo.class, LogInfo::message);
        logPrinter.log(jp, message, Logger::info);
    }
}