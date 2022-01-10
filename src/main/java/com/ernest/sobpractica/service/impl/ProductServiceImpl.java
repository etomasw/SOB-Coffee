package com.ernest.sobpractica.service.impl;

import com.ernest.sobpractica.model.Product;
import com.ernest.sobpractica.model.Store;
import com.ernest.sobpractica.repository.ProductRepository;
import com.ernest.sobpractica.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository service;

    /**
     * Inyeccion de dependencias a traves de Autowired
     * @param service
     */
    @Autowired
    public ProductServiceImpl(ProductRepository service) {
        this.service = service;
    }

    @Override
    public Product create(Product p) {
        return service.save(p);
    }

    @Override
    public Product update(Product p) {
        return service.save(p);
    }

    @Override
    public void delete(Long id) {
        service.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return service.findAll();
    }

    @Override
    public Product findProductByID(Long id) {
        return service.findById(id).orElse(null);
    }

    /**
     * Por implementar
     * @param store
     * @return
     */
    @Override
    public Product findByStore(Store store) {
        return null;
    }
}
