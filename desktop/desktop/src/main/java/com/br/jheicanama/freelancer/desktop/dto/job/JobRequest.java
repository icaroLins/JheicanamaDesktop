package com.br.jheicanama.freelancer.desktop.dto.job;

import com.br.jheicanama.freelancer.desktop.model.user.Contractor;
import lombok.Data;

@Data
public class JobRequest {
    private String title;
    private String description;
    private Double wage;
    private String area;
    private Contractor contractor;
}
