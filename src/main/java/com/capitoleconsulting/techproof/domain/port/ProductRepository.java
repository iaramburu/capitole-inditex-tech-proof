package com.capitoleconsulting.techproof.domain.port;

import com.capitoleconsulting.techproof.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
