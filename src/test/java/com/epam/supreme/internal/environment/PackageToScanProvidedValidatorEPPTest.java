package com.epam.supreme.internal.environment;

import com.epam.supreme.internal.exception.EnvironmentPropertyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PackageToScanProvidedValidatorEPPTest {

    private EnvironmentPostProcessor epp;

    @Mock
    private ConfigurableEnvironment environment;
    @Mock
    private SpringApplication application;

    @BeforeEach
    void setUp() {
        epp = new PackageToScanProvidedValidatorEPP();
    }

    @Test
    void shouldThrowExceptionWhenPackageToScanNotProvided() {
        when(environment.getProperty("method.log.packageToScan")).thenReturn(null);

        assertThatThrownBy(() -> epp.postProcessEnvironment(environment, application))
                .isInstanceOf(EnvironmentPropertyNotFoundException.class)
                .hasMessageContaining("method.log.packageToScan");
    }
}