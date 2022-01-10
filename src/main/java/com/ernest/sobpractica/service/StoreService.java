package com.ernest.sobpractica.service;

import com.ernest.sobpractica.model.Store;

import java.util.List;

public interface StoreService {
    Store create(Store s);
    Store update(Store s);
    void delete(Long id);
    List<Store> findAll();
    Store findStoreByID(Long id);
}
