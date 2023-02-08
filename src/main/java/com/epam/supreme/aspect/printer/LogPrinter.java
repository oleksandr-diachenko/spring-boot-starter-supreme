package com.epam.supreme.aspect.printer;

import com.epam.supreme.aspect.helper.LoggerResolver;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
@RequiredArgsConstructor
public class LogPrinter {

    private final LoggerResolver loggerResolver;

    public void log(JoinPoint jp, String message, BiConsumer<Logger, String> consumer) {
        Logger logger = loggerResolver.resolve(jp);
        consumer.accept(logger, message);
    }
}
