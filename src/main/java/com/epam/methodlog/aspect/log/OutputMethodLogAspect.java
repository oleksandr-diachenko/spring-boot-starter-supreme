package com.epam.methodlog.aspect.log;

import com.epam.methodlog.annotation.OutputMethodLog;
import com.epam.methodlog.aspect.log.logger.LogPrinter;
import com.epam.methodlog.aspect.log.logger.MessageInfo;
import com.epam.methodlog.utils.aspect.AspectMethodUtil;
import com.epam.methodlog.aspect.lookup.LoggerResolver;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static java.lang.String.format;

@Aspect
@Component
@RequiredArgsConstructor
public class OutputMethodLogAspect {

    private final AspectMethodUtil aspectMethodUtil;
    private final LoggerResolver loggerResolver;
    private final LogPrinter logPrinter;

    @Pointcut("@annotation(com.epam.methodlog.annotation.OutputMethodLog)")
    public void anyMethodAnnotatedWithOutputMethodLog() {
        // pointcut
    }

    @AfterReturning(value = "anyMethodAnnotatedWithOutputMethodLog()", returning = "retVal")
    public void logMethod(JoinPoint jp, Object retVal) {
        Method method = aspectMethodUtil.method(jp);
        MessageInfo messageInfo = new MessageInfo(jp, logger(jp), level(method), message(method, retVal));
        logPrinter.print(messageInfo);
    }

    private Level level(Method method) {
        OutputMethodLog annotation = method.getAnnotation(OutputMethodLog.class);
        return annotation.logLevel();
    }

    private String message(Method method, Object retVal) {
        return format("Method: '%s' returned: %s", method.getName(), retVal);
    }

    private Logger logger(JoinPoint jp) {
        return loggerResolver.resolve(jp);
    }
}