package com.br.jheicanama.freelancer.desktop.service.candidacy;

import com.br.jheicanama.freelancer.desktop.dto.candidacy.CandidacyResponse;
import com.br.jheicanama.freelancer.desktop.dto.job.JobResponse;
import com.br.jheicanama.freelancer.desktop.enums.StatusCandidacy;
import com.br.jheicanama.freelancer.desktop.model.candidacy.Candidacy;
import com.br.jheicanama.freelancer.desktop.model.job.JobVacancies;
import com.br.jheicanama.freelancer.desktop.model.user.Candidate;
import com.br.jheicanama.freelancer.desktop.repository.candidacy.CandidacyRepository;
import com.br.jheicanama.freelancer.desktop.repository.candidate.CandidateRepository;
import com.br.jheicanama.freelancer.desktop.repository.job.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidacyService {
    @Autowired
    private CandidacyRepository candidacyRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private JobRepository jobRepository;

    public CandidacyResponse candidatura(Long candidateId, Long jobId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candiadato não encontrado"));

        JobVacancies jobVacancies = jobRepository.findById(jobId)
                .orElseThrow(()->new RuntimeException("Vaga de emprego não encontrada"));

        Candidacy candidacy = new Candidacy();
        candidacy.setCandidate(candidate);
        candidacy.setJobVacancies(jobVacancies);

        candidacyRepository.save(candidacy);

        CandidacyResponse candidacyResponse = new CandidacyResponse(candidacy.getId(),candidate.getUsername(),
                jobVacancies.getTitle(), jobVacancies.getDescription(), jobVacancies.getWage(), jobVacancies.getArea(),
                jobVacancies.getContractor().getUsername(), jobVacancies.getContractor().getEmpresa(), candidacy.getStatus());
        return candidacyResponse;
    }

    public List<CandidacyResponse> listCandidacyJob(Long jobId){
        JobVacancies jobVacancies = jobRepository.findById(jobId)
                .orElseThrow(()-> new RuntimeException("Vaga de emprego não encontrada"));

        return candidacyRepository.findByJobVacancies_id(jobId).stream().map(response -> new CandidacyResponse(response.getId(),
                response.getCandidate().getUsername(), jobVacancies.getTitle(), jobVacancies.getDescription(), jobVacancies.getWage()
                , jobVacancies.getArea(), jobVacancies.getContractor().getUsername(), jobVacancies.getContractor().getEmpresa(), response.getStatus()
                )).toList();
    }

    public List<CandidacyResponse> listCandidacyCandidate(Long candidateId){
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

        return candidacyRepository.findByCandidate(candidate).stream().map(response -> new CandidacyResponse(response.getId(),
                response.getCandidate().getUsername(), response.getJobVacancies().getTitle(), response.getJobVacancies().getDescription(),
                response.getJobVacancies().getWage() , response.getJobVacancies().getArea(),
                response.getJobVacancies().getContractor().getUsername(), response.getJobVacancies().getContractor().getEmpresa(), response.getStatus()
        )).toList();

    }

    public CandidacyResponse aceptCandidacy(Long candidacyId, Long contractorId) {
        Candidacy candidacy = candidacyRepository.findById(candidacyId)
                .orElseThrow(()-> new RuntimeException("candidatura não encontrada"));

        JobVacancies vagas = candidacy.getJobVacancies();

        if(!vagas.getContractor().getId().equals(contractorId)){
            throw new RuntimeException("você não pode alterar essa candidatura");
        }

        candidacy.setStatus(StatusCandidacy.ACEITO);
        Candidacy newCandidacy =  candidacyRepository.save(candidacy);

        CandidacyResponse candidacyResponse = new CandidacyResponse(newCandidacy.getId(), newCandidacy.getCandidate().getUsername(),
                vagas.getTitle(), vagas.getDescription(), vagas.getWage(), vagas.getArea(), vagas.getContractor().getUsername(), vagas.getContractor().getEmpresa(), newCandidacy.getStatus());
        return candidacyResponse;
    }

    public CandidacyResponse recuseCandidacy(Long candidacyId, Long contractorId) {
        Candidacy candidacy = candidacyRepository.findById(candidacyId)
                .orElseThrow(()-> new RuntimeException("candidatura não encontrada"));

        JobVacancies vagas = candidacy.getJobVacancies();

        if(!vagas.getContractor().getId().equals(contractorId)){
            throw new RuntimeException("você não pode alterar essa candidatura");
        }

        candidacy.setStatus(StatusCandidacy.RECUSADO);
        Candidacy newCandidacy =  candidacyRepository.save(candidacy);

        CandidacyResponse candidacyResponse = new CandidacyResponse(newCandidacy.getId(), newCandidacy.getCandidate().getUsername(),
                vagas.getTitle(), vagas.getDescription(), vagas.getWage(), vagas.getArea(), vagas.getContractor().getUsername(), vagas.getContractor().getEmpresa(), newCandidacy.getStatus());
        return candidacyResponse;
    }
}
