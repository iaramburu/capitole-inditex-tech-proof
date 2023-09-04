package com.capitoleconsulting.techproof.domain.service;

import com.capitoleconsulting.techproof.domain.entity.Product;
import com.capitoleconsulting.techproof.domain.entity.Size;
import com.capitoleconsulting.techproof.domain.port.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.capitoleconsulting.techproof.Builders.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void testFindAll() {
        List<Product> productList = Arrays.asList(product1(), product2(), product3(), product4(), product5());
        List<Long> expected = Arrays.asList(5L, 1L, 3L);

        doReturn(productList).when(productRepository).findAll();

        List<Long> productFindAllOdtoList = this.productService.findAll();

        assertThat(productFindAllOdtoList)
                .isNotNull()
                .isEqualTo(expected);
    }

    @Test
    void testFindAllWithEmptyProductList() {
        List<Product> productList = Collections.singletonList(productWithoutSizes());
        List<Long> expected = new ArrayList<>();

        doReturn(productList).when(productRepository).findAll();

        List<Long> productFindAllOdtoList = this.productService.findAll();

        assertThat(productFindAllOdtoList)
                .isNotNull()
                .isEqualTo(expected);
    }

    @Test
    void testFindAllAndProductWithSpecialAndNoSpecialWithStock() {
        Size size41 = size41().toBuilder()
                .stock(stock41().toBuilder().quantity(1).build())
                .build();
        Product product4 = product4().toBuilder()
                .sizeList(Arrays.asList(size41, size42(), size43(), size44()))
                .build();
        List<Product> productList = Arrays.asList(product1(), product2(), product3(), product4, product5());
        List<Long> expected = Arrays.asList(5L, 1L, 4L, 3L);

        doReturn(productList).when(productRepository).findAll();

        List<Long> productFindAllOdtoList = this.productService.findAll();

        assertThat(productFindAllOdtoList)
                .isNotNull()
                .isEqualTo(expected);
    }
}