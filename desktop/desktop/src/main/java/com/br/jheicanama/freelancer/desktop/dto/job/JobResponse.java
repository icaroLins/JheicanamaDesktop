package com.br.jheicanama.freelancer.desktop.dto.job;

import lombok.Data;

@Data
public class JobResponse {
    private String title;
    private String description;
    private Double wage;
    private String area;
    private Long id;
    private String nameContractor;
    private String empresa;

    public JobResponse(String title, String description, Double wage, String area, Long id, String nameContractor, String empresa) {
        this.title = title;
        this.description = description;
        this.wage = wage;
        this.area = area;
        this.id = id;
        this.nameContractor = nameContractor;
        this.empresa = empresa;
    }
}
