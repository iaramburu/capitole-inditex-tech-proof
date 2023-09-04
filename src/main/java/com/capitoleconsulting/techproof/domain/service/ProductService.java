package com.capitoleconsulting.techproof.domain.service;

import com.capitoleconsulting.techproof.domain.entity.Product;
import com.capitoleconsulting.techproof.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Long> findAll() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .filter(Product::hasStock)
                .sorted(Comparator.comparing(Product::getSequence))
                .map(Product::getId)
                .toList();
    }
}
