package com.epam.methodlog.aspect.log;

import com.epam.methodlog.annotation.InputMethodLog;
import com.epam.methodlog.aspect.log.logger.LogPrinter;
import com.epam.methodlog.aspect.log.logger.MessageInfo;
import com.epam.methodlog.aspect.lookup.LoggerResolver;
import com.epam.methodlog.utils.aspect.AspectMethodUtil;
import com.epam.methodlog.utils.formatter.StringFormatter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

import static java.lang.String.format;

@Aspect
@Component
@RequiredArgsConstructor
public class InputMethodLogAspect {

    private final AspectMethodUtil aspectMethodUtil;
    private final LoggerResolver loggerResolver;
    private final StringFormatter<Map<String, Object>> mapStringFormatter;
    private final LogPrinter logPrinter;

    @Pointcut("@annotation(com.epam.methodlog.annotation.InputMethodLog)")
    public void anyMethodAnnotatedWithInputMethodLog() {
        // pointcut
    }

    @Before(value = "anyMethodAnnotatedWithInputMethodLog()")
    public void logMethod(JoinPoint jp) {
        String parameters = parameters(jp);
        Method method = aspectMethodUtil.method(jp);
        MessageInfo messageInfo = new MessageInfo(jp, logger(jp), level(method), message(parameters, method));
        logPrinter.print(messageInfo);
    }

    private String parameters(JoinPoint jp) {
        Map<String, Object> args = aspectMethodUtil.paramsToArgs(jp);
        return mapStringFormatter.format(args);
    }

    private String message(String parameters, Method method) {
        return format("Method: '%s' was called with parameters: %s", method.getName(), parameters);
    }

    private Logger logger(JoinPoint jp) {
        return loggerResolver.resolve(jp);
    }

    private Level level(Method method) {
        InputMethodLog annotation = method.getAnnotation(InputMethodLog.class);
        return annotation.logLevel();
    }
}