package com.capitoleconsulting.techproof.infrastructure.config;

import com.capitoleconsulting.techproof.domain.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Profile("mock-services")
@Configuration
public class MockServicesConfig {

    @Bean
    @Primary
    public ProductService productServiceMock() {
        return mock(ProductService.class);
    }
}
