package com.github.oleksandrdiachenko.supreme;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@Import(MethodLogAutoConfig.class)
public class TestConfig {
}