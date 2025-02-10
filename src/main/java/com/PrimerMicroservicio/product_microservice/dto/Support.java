package com.PrimerMicroservicio.product_microservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Support {
    private String url;   // URL de soporte
    private String text;  // Texto de soporte

    // Constructor
    public Support(String url, String text) {
        this.url = url;
        this.text = text;
    }
}