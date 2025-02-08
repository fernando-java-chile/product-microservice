package com.PrimerMicroservicio.product_microservice.controller;
import com.PrimerMicroservicio.product_microservice.entity.ProductEntity;
import com.PrimerMicroservicio.product_microservice.repository.ProductRepository;
import com.PrimerMicroservicio.product_microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductEntity>> getProducts() {
        var products = productRepository.findAll();
        return ResponseEntity.ok().body(productRepository.findAll());

    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable("id") String id) {
        // return ResponseEntity.ok().body(productRepository.findById(id).get()); // ❌ PELIGRO: Puede lanzar una excepción
        return productRepository.findById(id)
                .map(ResponseEntity::ok)  // Si el producto existe, lo devuelve con estado 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no existe, devuelve 404 Not Found
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductEntity> getProductByName(@PathVariable("name") String name) {
        return productService.getProductByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        try {
            ProductEntity savedProduct = productRepository.save(product);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedProduct.getId())
                    .toUri();

            return ResponseEntity.created(location).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

