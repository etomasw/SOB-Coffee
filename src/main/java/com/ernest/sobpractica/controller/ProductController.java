package com.ernest.sobpractica.controller;

import com.ernest.sobpractica.model.HttpResponse;
import com.ernest.sobpractica.model.Product;
import com.ernest.sobpractica.model.Store;
import com.ernest.sobpractica.service.ProductService;
import com.ernest.sobpractica.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/rest/api/product")
public class ProductController {

    private ProductService productService;
    private StoreService storeService;

    @Autowired
    public ProductController(ProductService productService, StoreService storeService) {
        this.productService = productService;
        this.storeService = storeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.create(product);
        return new ResponseEntity<>(product, CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.findProductByID(id);
        if(product == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, OK);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id) {
        Product p = productService.findProductByID(id);
        return new ResponseEntity<>(productService.update(p), OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.findAll(), OK);
    }

    @GetMapping("/findStore")
    public ResponseEntity<?> getStoreProducts(@RequestParam("storeName") String storeName) {
        List<Store> stores = storeService.findAll();
        for(Store s : stores) {
            if(s.getStoreName().contains(storeName)) {
                return new ResponseEntity<>(s.getProducts(), OK);
            }
        }
        return response(BAD_REQUEST, "Error 400. No has especificat cap missatge");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
