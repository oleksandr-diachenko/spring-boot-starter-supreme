package com.epam.methodlog.aspect.log;

import com.epam.methodlog.aspect.log.level.Logger;
import com.epam.methodlog.aspect.lookup.AspectMethodLookup;
import com.epam.methodlog.aspect.lookup.AspectMethodParametersLookup;
import com.epam.methodlog.utils.formatter.StringFormatter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class InputMethodLogAspect {

    private final AspectMethodLookup aspectMethodLookup;
    private final AspectMethodParametersLookup aspectMethodParametersLookup;
    private final StringFormatter<Map<String, Object>> mapStringFormatter;
    private final Logger logger;

    public InputMethodLogAspect(AspectMethodLookup aspectMethodLookup,
                                AspectMethodParametersLookup aspectMethodParametersLookup,
                                StringFormatter<Map<String, Object>> mapStringFormatter,
                                Logger logger) {
        this.aspectMethodLookup = aspectMethodLookup;
        this.aspectMethodParametersLookup = aspectMethodParametersLookup;
        this.mapStringFormatter = mapStringFormatter;
        this.logger = logger;
    }

    @Pointcut("@annotation(com.epam.methodlog.annotation.InputMethodLog)")
    public void anyMethodAnnotatedWithInputMethodLog() {
        // pointcut
    }

    @Before(value = "anyMethodAnnotatedWithInputMethodLog()")
    public void logMethod(JoinPoint jp) {
        Map<String, Object> args = aspectMethodParametersLookup.lookup(jp);
        String parameters = mapStringFormatter.format(args);
        Method method = aspectMethodLookup.lookup(jp);
        logger.log(jp, "Method: '{}' was called with parameters: {}", method.getName(), parameters);
    }
}