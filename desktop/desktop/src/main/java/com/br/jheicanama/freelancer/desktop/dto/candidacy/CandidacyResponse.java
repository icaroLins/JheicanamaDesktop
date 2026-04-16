package com.br.jheicanama.freelancer.desktop.dto.candidacy;

import com.br.jheicanama.freelancer.desktop.enums.StatusCandidacy;
import lombok.Data;

@Data
public class CandidacyResponse {
    private Long id;
    private String candidateName;
    private String jobTitle;
    private String jobDescription;
    private Double jobWage;
    private String jobArea;
    private String contractorName;
    private String contractorEmpresa;
    private StatusCandidacy status;

    public CandidacyResponse(Long id, String candidateName,
                             String jobTitle, String jobDescription, Double jobWage,
                             String jobArea, String contractorName,
                             String contractorEmpresa, StatusCandidacy status) {
        this.id = id;
        this.candidateName = candidateName;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobWage = jobWage;
        this.jobArea = jobArea;
        this.contractorName = contractorName;
        this.contractorEmpresa = contractorEmpresa;
        this.status = status;
    }

    public CandidacyResponse(String jobTitle, String contractorEmpresa, StatusCandidacy status) {
        this.jobTitle = jobTitle;
        this.contractorEmpresa = contractorEmpresa;
        this.status = status;
    }

    public CandidacyResponse(Long id, String candidateName, StatusCandidacy status) {
        this.id = id;
        this.candidateName = candidateName;
        this.status = status;
    }
}
