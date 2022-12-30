package com.epam.methodlog.aspect.log.logger;

import org.slf4j.event.Level;

public interface Logger {

    boolean isApplicableFor(Level level);

    void log(MessageInfo messageInfo);
}
