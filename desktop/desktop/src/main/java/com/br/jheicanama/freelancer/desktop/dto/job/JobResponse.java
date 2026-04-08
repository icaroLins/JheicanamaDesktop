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
}
