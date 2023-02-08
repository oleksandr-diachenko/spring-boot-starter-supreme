package com.epam.supreme.aspect.helper;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ParamsResolver {

    private final MethodResolver methodResolver;

    public Map<String, Object> resolve(JoinPoint jp) {
        Method method = methodResolver.resolve(jp);
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
