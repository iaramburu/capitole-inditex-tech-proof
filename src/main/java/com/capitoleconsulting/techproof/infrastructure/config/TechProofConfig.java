package com.capitoleconsulting.techproof.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.capitoleconsulting.techproof.infrastructure.utils.JsonUtils.createMapper;

@Configuration
public class TechProofConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return createMapper();
    }
}
