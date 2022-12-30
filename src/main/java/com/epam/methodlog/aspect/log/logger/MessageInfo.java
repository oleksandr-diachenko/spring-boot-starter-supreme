package com.epam.methodlog.aspect.log.logger;

import lombok.Builder;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.event.Level;

@Builder
public record MessageInfo(JoinPoint joinPoint, Logger logger, Level level, String message) {
}
