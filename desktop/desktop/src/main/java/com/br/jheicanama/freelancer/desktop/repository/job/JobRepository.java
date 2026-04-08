package com.br.jheicanama.freelancer.desktop.repository.job;

import com.br.jheicanama.freelancer.desktop.model.job.JobVacancies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository  extends JpaRepository<JobVacancies, Long> {
    JobVacancies findByContractorId(Long contractorId);
}
