package com.github.oleksandrdiachenko.supreme.aspect.helper;

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

    public Map<Integer, Object> resolve(JoinPoint jp) {
        Method method = methodResolver.resolve(jp);
        Parameter[] parameters = method.getParameters();
        Object[] args = jp.getArgs();
        Map<Integer, Object> result = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            Object value = args[i];
            result.put(i, value);
        }
        return result;
    }
}
