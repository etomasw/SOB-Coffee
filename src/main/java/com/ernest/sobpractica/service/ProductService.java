package com.ernest.sobpractica.service;

import com.ernest.sobpractica.model.Product;
import com.ernest.sobpractica.model.Store;

import java.util.List;

public interface ProductService {

    Product create(Product p);
    Product update(Product p);
    void delete(Long id);
    List<Product> findAll();
    Product findProductByID(Long id);
    Product findByStore(Store store);
}
