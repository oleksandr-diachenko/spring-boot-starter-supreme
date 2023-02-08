package com.epam.supreme.aspect.template;

import com.epam.supreme.aspect.helper.MethodResolver;
import com.epam.supreme.aspect.template.custom.constructor.CustomMessageConstructor;
import com.epam.supreme.aspect.template.defaults.constructor.DefaultMessageConstructor;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class MessageResolver<T extends Annotation> {

    private final MethodResolver methodResolver;
    private final DefaultMessageConstructor defaultMessageConstructor;
    private final CustomMessageConstructor customMessageConstructor;

    public String resolve(JoinPoint jp, @Nullable Object retVal, Class<T> clazz, Function<T, String> function) {
        Method method = methodResolver.resolve(jp);
        T annotation = method.getAnnotation(clazz);
        String annotationTemplate = function.apply(annotation);
        if (annotationTemplate.isEmpty()) {
            return defaultMessageConstructor.construct(jp, retVal);
        }
        return customMessageConstructor.construct(jp, annotationTemplate, retVal);
    }
}
