package com.PrimerMicroservicio.product_microservice.repository;

import com.PrimerMicroservicio.product_microservice.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


// La interfaz ProductRepository extiende de MongoRepository, que es una interfaz de Spring Data MongoDB.
public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    // Buscar un producto por nombre
    Optional<ProductEntity> findByProductName(String productName);

    // Buscar productos con precio menor a un valor específico
    List<ProductEntity> findByUnitPriceLessThan(Double price);

    // Buscar productos que contengan cierta palabra en la descripción
    List<ProductEntity> findByProductDescriptionContaining(String keyword);



}

