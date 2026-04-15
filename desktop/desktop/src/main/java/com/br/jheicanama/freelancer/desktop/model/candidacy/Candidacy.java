package com.br.jheicanama.freelancer.desktop.model.candidacy;

import com.br.jheicanama.freelancer.desktop.enums.StatusCandidacy;
import com.br.jheicanama.freelancer.desktop.model.job.JobVacancies;
import com.br.jheicanama.freelancer.desktop.model.user.Candidate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Candidacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_vancancy_id")
    private JobVacancies jobVacancies;

    @Enumerated(EnumType.STRING)
    private StatusCandidacy status = StatusCandidacy.PENDENTE;
}
