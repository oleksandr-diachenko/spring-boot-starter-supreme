package com.epam.methodlog.utils.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Component
public class AspectMethodUtil {

    public Method method(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        return signature.getMethod();
    }

    public Map<String, Object> paramsToArgs(JoinPoint jp) {
        Method method = method(jp);
        Parameter[] parameters = method.getParameters();
        Object[] args = jp.getArgs();
        Map<String, Object> result = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            String name = parameters[i].getName();
            Object value = args[i];
            result.put(name, value);
        }
        return result;
    }
}