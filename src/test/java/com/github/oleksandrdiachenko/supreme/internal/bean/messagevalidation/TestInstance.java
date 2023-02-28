package com.github.oleksandrdiachenko.supreme.internal.bean.messagevalidation;

import com.github.oleksandrdiachenko.supreme.annotation.*;

public class TestInstance {

    @LogDebug(message = "Message form LogDebug")
    @LogInfo(message = "Message form LogInfo")
    @LogWarn(message = "Message form LogWarn")
    @LogTrace(message = "Message form LogTrace")
    @LogError(message = "Message form LogError")
    public void doSomething() {
        //do nothing
    }
}
