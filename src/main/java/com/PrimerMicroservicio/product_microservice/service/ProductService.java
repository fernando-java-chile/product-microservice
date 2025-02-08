package com.PrimerMicroservicio.product_microservice.service;

import com.PrimerMicroservicio.product_microservice.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    /**
     * Busca un producto por nombre.
     * @param productName Nombre del producto a buscar.
     * @return Producto con el nombre indicado.
     */
    Optional<ProductEntity> getProductByName(String productName);

    /**
     * Busca productos con un precio menor al indicado.
     * @param price Precio máximo de los productos a buscar.
     * @return Lista de productos con precio menor al indicado.
     */
    List<ProductEntity> findByUnitPriceLessThan(Double price);

    /**
     * Busca productos que contengan cierta palabra en la descripción.
     * @param keyword Palabra clave a buscar en la descripción.
     * @return Lista de productos que contienen la palabra clave en la descripción.
     */
    List<ProductEntity> getProductsByDescriptionContaining(String keyword);
}
