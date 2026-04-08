package com.br.jheicanama.freelancer.desktop.model.job;

import com.br.jheicanama.freelancer.desktop.model.user.Contractor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JobVacancies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double wage;
    private String area;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

}
