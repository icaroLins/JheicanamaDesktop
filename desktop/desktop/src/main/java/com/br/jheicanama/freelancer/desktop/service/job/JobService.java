package com.br.jheicanama.freelancer.desktop.service.job;

import com.br.jheicanama.freelancer.desktop.dto.job.JobRequest;
import com.br.jheicanama.freelancer.desktop.dto.job.JobResponse;
import com.br.jheicanama.freelancer.desktop.model.job.JobVacancies;
import com.br.jheicanama.freelancer.desktop.repository.job.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public JobResponse createJob(JobRequest jobRequest) {
        JobVacancies jobVacancies = new JobVacancies();

        jobVacancies.setTitle(jobRequest.getTitle());
        jobVacancies.setDescription(jobRequest.getDescription());
        jobVacancies.setWage(jobRequest.getWage());
        jobVacancies.setArea(jobRequest.getArea());
        jobVacancies.setContractor(jobRequest.getContractor());

        JobVacancies jobcrate = jobRepository.save(jobVacancies);

        JobResponse jobResponse = new JobResponse();

        jobResponse.setId(jobcrate.getId());
        jobResponse.setTitle(jobcrate.getTitle());
        jobResponse.setDescription(jobcrate.getDescription());
        jobResponse.setWage(jobcrate.getWage());
        jobResponse.setArea(jobcrate.getArea());
        jobResponse.setNameContractor(jobcrate.getContractor().getUsername());
        jobResponse.setEmpresa(jobcrate.getContractor().getEmpresa());

        return jobResponse;
    }



}
