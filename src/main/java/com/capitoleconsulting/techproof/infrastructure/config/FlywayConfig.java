package com.capitoleconsulting.techproof.infrastructure.config;

import com.capitoleconsulting.techproof.infrastructure.config.callback.CsvCallback;
import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class FlywayConfig {

    @Bean
    @Primary
    public FlywayConfigurationCustomizer flywayConfigurationCustomizer(ResourceLoader resourceLoader) {
        return configuration -> configuration.callbacks(new CsvCallback(resourceLoader));
    }
}
