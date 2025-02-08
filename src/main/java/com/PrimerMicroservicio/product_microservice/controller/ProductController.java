package com.PrimerMicroservicio.product_microservice.controller;
import com.PrimerMicroservicio.product_microservice.entity.ProductEntity;
import com.PrimerMicroservicio.product_microservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;



    @GetMapping
    public ResponseEntity<List<ProductEntity>> getProducts() {
        var products = productRepository.findAll();
        return ResponseEntity.ok().body(productRepository.findAll());

    }


    @GetMapping("/id")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(productRepository.findById(id).get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductEntity product) {

        productRepository.save(product);
    }
}

