package com.PrimerMicroservicio.product_microservice.controller;

import com.PrimerMicroservicio.product_microservice.dto.Response;
import com.PrimerMicroservicio.product_microservice.dto.Support;
import com.PrimerMicroservicio.product_microservice.entity.ProductEntity;
import com.PrimerMicroservicio.product_microservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/products")
public class ProductControllerV2 {
    @Autowired
    @Lazy
    ProductRepository productRepository;

    /**
     * Obtiene todos los productos paginados.
     * Samples:  http://desktop-3k1k56l:8090/sistema/api/v2/products?page=0&perPage=3
     * @param page   Página actual (por defecto 0)
     * @param perPage Elementos por página (por defecto 10)
     * @return Lista de productos paginados
     */
    @GetMapping
    public ResponseEntity<Response<ProductEntity>> getProducts(
            @RequestParam(defaultValue = "0") int page,      // Página actual (por defecto 0)
            @RequestParam(defaultValue = "10") int perPage  // Elementos por página (por defecto 10)
    ) {
        // Configura la paginación
        Pageable pageable = PageRequest.of(page, perPage);

        // Obtiene los productos paginados
        Page<ProductEntity> productPage = productRepository.findAll(pageable);

        // Verifica si hay productos
        if (productPage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Crea la respuesta estandarizada
        Response<ProductEntity> response = new Response<>(
                productPage.getNumber(),         // Página actual
                productPage.getSize(),           // Elementos por página
                productPage.getTotalElements(),  // Total de elementos
                productPage.getTotalPages(),     // Total de páginas
                productPage.getContent(),        // Lista de productos
                new Support("https://example.com/support", "Contact us for support") // Soporte
        );
        return ResponseEntity.ok(response);
    }


    /**
     * Obtener todos los productos con precio menor al especificado
     * Samples:  http://desktop-3k1k56l:8090/sistema/api/v2/products/price/1000?page=0&perPage=2
     * @param {id} ID del producto
     * @return Productos encontrados
     */
    @GetMapping("/price/{price}")
    public ResponseEntity<Response<ProductEntity>> getProductsByUnitPriceLessThan(
            @PathVariable("price") Double price,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage
    ) {
        // Obtener todos los productos con precio menor al especificado
        List<ProductEntity> allProducts = productRepository.findByUnitPriceLessThan(price);

        // Calcular el índice de inicio y fin para la paginación
        int start = page * perPage;
        int end = Math.min(start + perPage, allProducts.size());

        // Verificar si hay productos en el rango solicitado
        if (start >= allProducts.size()) {
            return ResponseEntity.notFound().build();
        }

        // Obtener la sublista de productos para la página actual
        List<ProductEntity> products = allProducts.subList(start, end);

        // Crear la respuesta estandarizada
        Response<ProductEntity> response = new Response<>(
                page,                          // Página actual
                perPage,                       // Elementos por página
                allProducts.size(),            // Total de elementos
                (int) Math.ceil((double) allProducts.size() / perPage), // Total de páginas
                products,                      // Lista de productos para la página actual
                new Support("https://example.com/support", "Contact us for support") // Soporte
        );

        return ResponseEntity.ok(response);
    }

}
