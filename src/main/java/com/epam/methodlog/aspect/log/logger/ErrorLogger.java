package com.epam.methodlog.aspect.log.logger;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

import static org.slf4j.event.Level.ERROR;

@Component
class ErrorLogger implements Logger {

    @Override
    public boolean isApplicableFor(Level level) {
        return level == ERROR;
    }

    @Override
    public void log(MessageInfo messageInfo) {
        String message = messageInfo.message();
        messageInfo.logger().error(message);
    }
}
