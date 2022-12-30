package com.epam.methodlog.aspect.log.logger;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

import static org.slf4j.event.Level.TRACE;

@Component
class TraceLogger implements Logger {

    @Override
    public boolean isApplicableFor(Level level) {
        return level == TRACE;
    }

    @Override
    public void log(MessageInfo messageInfo) {
        String message = messageInfo.message();
        messageInfo.logger().trace(message);
    }
}
