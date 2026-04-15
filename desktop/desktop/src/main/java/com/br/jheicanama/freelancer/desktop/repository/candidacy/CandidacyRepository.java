package com.br.jheicanama.freelancer.desktop.repository.candidacy;

import com.br.jheicanama.freelancer.desktop.model.candidacy.Candidacy;
import com.br.jheicanama.freelancer.desktop.model.job.JobVacancies;
import com.br.jheicanama.freelancer.desktop.model.user.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidacyRepository extends JpaRepository<Candidacy, Long> {
    List<Candidacy> findByCandidateAndJobVacancies(Candidate candidate, JobVacancies jobVacancies);
    List<Candidacy> findByCandidate(Candidate candidate);
    List<Candidacy>findByJobVacancies(JobVacancies jobVacancies);
    List<Candidacy> findByJobVacancies_id(Long jobVacanciesId);
}
