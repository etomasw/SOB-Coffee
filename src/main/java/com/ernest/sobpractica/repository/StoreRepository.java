package com.ernest.sobpractica.repository;

import com.ernest.sobpractica.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
