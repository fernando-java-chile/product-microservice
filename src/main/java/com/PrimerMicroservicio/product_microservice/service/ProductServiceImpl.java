package com.PrimerMicroservicio.product_microservice.service;

import com.PrimerMicroservicio.product_microservice.entity.ProductEntity;
import com.PrimerMicroservicio.product_microservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<ProductEntity> getProductByName(String productName) {
        if(productName != null) {
            return productRepository.findByProductName(productName);
        }
        return Optional.empty();
    }

    @Override
    public List<ProductEntity> findByUnitPriceLessThan(Double price) {
        if (price == null) {
            return Collections.emptyList(); // Devuelve una lista vacía en lugar de null
        }
        return productRepository.findByUnitPriceLessThan(price);
    }

    @Override
    public List<ProductEntity> getProductsByDescriptionContaining(String keyword) {
        if (keyword == null) {
            return Collections.emptyList(); // Devuelve una lista vacía en lugar de null
        }
        return productRepository.findByProductDescriptionContaining(keyword);
    }
}
