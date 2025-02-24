package com.PrimerMicroservicio.product_microservice.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
@Getter
@Setter
@RefreshScope
public class TestBrokerController {
    @Value("${app.testProp}")
    private String testProp;

    // http://desktop-3k1k56l:8085/sistema/api/actuator/busrefresh
    // actualiza las propiedades de configuración de los microservicios
    // Message Broker, Refresh Configuration, RabbitMQ, Docker
    @GetMapping("/test")
    public String test() {
        return testProp;
    }


}
