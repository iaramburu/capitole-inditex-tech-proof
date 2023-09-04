package com.capitoleconsulting.techproof.application;

import com.capitoleconsulting.techproof.domain.port.ProductApi;
import com.capitoleconsulting.techproof.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    public List<Long> findAll() {
        return this.productService.findAll();
    }
}
