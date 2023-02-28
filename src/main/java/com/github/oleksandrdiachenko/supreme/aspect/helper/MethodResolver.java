package com.github.oleksandrdiachenko.supreme.aspect.helper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MethodResolver {

    public Method resolve(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        return signature.getMethod();
    }
}
