package com.br.jheicanama.freelancer.desktop.repository.job;

import com.br.jheicanama.freelancer.desktop.model.job.JobVacancies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository  extends JpaRepository<JobVacancies, Long> {
    List<JobVacancies> findByContractorId(Long contractorId);
}
