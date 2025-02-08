package com.PrimerMicroservicio.product_microservice.service;

import com.PrimerMicroservicio.product_microservice.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductEntity> getProductByName(String productName);
    List<ProductEntity> getProductsByPrice(Double price);
    List<ProductEntity> getProductsByDescription(String keyword);
}
