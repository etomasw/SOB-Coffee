package com.ernest.sobpractica.repository;

import com.ernest.sobpractica.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
