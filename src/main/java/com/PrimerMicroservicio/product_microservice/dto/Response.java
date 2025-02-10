package com.PrimerMicroservicio.product_microservice.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
public class Response<T> {
    private int page;        // Número de página actual
    private int perPage;     // Elementos por página
    private long total;      // Total de elementos
    private int totalPages;  // Total de páginas
    private List<T> data;    // Lista de cualquier tipo de objeto
    private Support support; // Información de soporte

    // Constructor
    public Response(int page, int perPage, long total, int totalPages, List<T> data, Support support) {
        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.totalPages = totalPages;
        this.data = data;
        this.support = support;
    }
}
