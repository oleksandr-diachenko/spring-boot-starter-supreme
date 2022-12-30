package com.epam.methodlog.aspect.log.logger;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LogPrinterImpl implements LogPrinter {

    private final List<Logger> loggers;

    @Override
    public void print(MessageInfo messageInfo) {
        loggers.stream()
                .filter(l -> l.isApplicableFor(messageInfo.level()))
                .findFirst()
                .ifPresent(l -> l.log(messageInfo));
    }
}
