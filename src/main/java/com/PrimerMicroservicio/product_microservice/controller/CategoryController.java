package com.PrimerMicroservicio.product_microservice.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/categories")
@Getter
@Setter
@RefreshScope
public class CategoryController {

    // Agregarlo a las configuraciones externas del Microservicio
    @Value("${app.testProp}")
    private String testProp;


    // cambio del valor desde github a través del sistema config-server
    // @RefreshScope: Refrescando las Configuraciones
    // para ejecurtar los cambios en tiempo de ejecución
    // POST a http://192.168.100.14:8085/sistema/api/actuator/refresh
    // y luego 192.168.100.14:8085/sistema/api/v1/categories/test
    @GetMapping("/test")
    public String test() {
        return testProp;
    }


}
