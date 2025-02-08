package com.PrimerMicroservicio.product_microservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Especifica la colección en MongoDB donde se almacenarán los documentos
@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor      // Crea un constructor sin argumentos.
@AllArgsConstructor     // Crea un constructor con todos los argumentos.
public class ProductEntity {
    @Id  // Indica que este campo será el identificador único en MongoDB
    private String id;

    private String productName;
    private String productDescription;
    private Double unitPrice;
}

