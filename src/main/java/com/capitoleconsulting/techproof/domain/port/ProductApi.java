package com.capitoleconsulting.techproof.domain.port;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface ProductApi {

    @GetMapping("/products")
    List<Long> findAll();
}
