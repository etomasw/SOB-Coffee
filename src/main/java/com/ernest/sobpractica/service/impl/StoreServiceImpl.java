package com.ernest.sobpractica.service.impl;

import com.ernest.sobpractica.model.Store;
import com.ernest.sobpractica.repository.StoreRepository;
import com.ernest.sobpractica.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store create(Store s) {
        return storeRepository.save(s);
    }

    @Override
    public Store update(Store s) {
        return storeRepository.save(s);
    }

    @Override
    public void delete(Long id) {
        storeRepository.deleteById(id);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store findStoreByID(Long id) {
        return storeRepository.findById(id).orElse(null);
    }
}
