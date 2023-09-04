package com.capitoleconsulting.techproof.infrastructure.config.callback;

import com.capitoleconsulting.techproof.domain.entity.Product;
import com.capitoleconsulting.techproof.domain.port.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static com.capitoleconsulting.techproof.Builders.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = { "mock-services", "db-test" })
@Sql(scripts = {"/scripts/clean-data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CsvCallbackTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindAll() {
        List<Product> expected = Arrays.asList(product1(), product2(), product3(), product4(), product5());

        List<Product> retrieved = this.productRepository.findAll();

        assertThat(retrieved)
                .isNotNull()
                .isEqualTo(expected);
    }
}