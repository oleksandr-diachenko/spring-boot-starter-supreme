package com.epam.methodlog.aspect.log;

import com.epam.methodlog.aspect.log.level.Logger;
import com.epam.methodlog.aspect.lookup.AspectMethodLookup;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class OutputMethodLogAspect {

    private final AspectMethodLookup aspectMethodLookup;
    private final Logger logger;

    public OutputMethodLogAspect(AspectMethodLookup aspectMethodLookup, Logger logger) {
        this.aspectMethodLookup = aspectMethodLookup;
        this.logger = logger;
    }

    @Pointcut("@annotation(com.epam.methodlog.annotation.OutputMethodLog)")
    public void anyMethodAnnotatedWithOutputMethodLog() {
        // pointcut
    }

    @AfterReturning(value = "anyMethodAnnotatedWithOutputMethodLog()", returning = "retVal")
    public void logMethod(JoinPoint jp, Object retVal) {
        Method method = aspectMethodLookup.lookup(jp);
        logger.log(jp, "Method: '{}' returned: {}", method.getName(), retVal);
    }
}