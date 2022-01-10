package com.ernest.sobpractica.controller;

import com.ernest.sobpractica.model.Product;
import com.ernest.sobpractica.model.Store;
import com.ernest.sobpractica.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/rest/api/store")
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Store> createProduct(@RequestBody Store store) {
        storeService.create(store);
        return new ResponseEntity<>(store, CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable("id") Long id) {
        Store store = storeService.findStoreByID(id);
        if(store == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(store, OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Store>> getAllStores() {
        return new ResponseEntity<>(storeService.findAll(), OK);
    }

}
