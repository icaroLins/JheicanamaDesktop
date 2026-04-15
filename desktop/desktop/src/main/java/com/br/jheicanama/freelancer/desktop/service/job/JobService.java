package com.br.jheicanama.freelancer.desktop.service.job;

import com.br.jheicanama.freelancer.desktop.dto.job.JobRequest;
import com.br.jheicanama.freelancer.desktop.dto.job.JobResponse;
import com.br.jheicanama.freelancer.desktop.model.job.JobVacancies;
import com.br.jheicanama.freelancer.desktop.repository.job.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        JobResponse jobResponse = new JobResponse(jobcrate.getTitle(),
                jobcrate.getDescription(), jobcrate.getWage(),jobcrate.getArea(),
                jobcrate.getId(),jobcrate.getContractor().getUsername(),jobcrate.getContractor().getEmpresa());

        return jobResponse;
    }

    public List<JobResponse> listJob() {
        return jobRepository.findAll().stream().map(response -> new JobResponse(
                response.getTitle(),
                response.getDescription(),
                response.getWage(),
                response.getArea(),
                response.getId(),
                response.getContractor().getUsername(),
                response.getContractor().getEmpresa())).toList();
    }

    public JobResponse updateJob(JobRequest jobRequest, Long contractorId, Long jobId) {
        JobVacancies jobVacancies = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("vaga de emprego não encontrada"));
        if(!jobVacancies.getContractor().getId().equals(contractorId)){
            throw new RuntimeException("Você não pode editar essa vaga");
        }

        JobVacancies job = new JobVacancies();
        job.setDescription(jobRequest.getDescription());
        job.setWage(jobRequest.getWage());
        job.setArea(jobRequest.getArea());
        job.setTitle(jobRequest.getTitle());

        JobVacancies jobUpdate = jobRepository.save(job);

        JobResponse jobResponse = new JobResponse(jobUpdate.getTitle(),jobUpdate.getDescription(),
                jobUpdate.getWage(),jobUpdate.getArea(),jobUpdate.getId(),jobUpdate.getContractor().getUsername()
                ,jobUpdate.getContractor().getEmpresa());

        return jobResponse;
    }

    public List<JobResponse> listContractorJob(Long contractorId) {
        return jobRepository.findByContractorId(contractorId).stream()
                .map( response -> new JobResponse(response.getTitle(),
                        response.getDescription(),
                        response.getWage(),
                        response.getArea(),
                        response.getId(),
                        response.getContractor().getUsername(),
                        response.getContractor().getEmpresa())).toList();
    }

    public void deleteJob(Long contractorId, Long jobId) {
        JobVacancies jobVacancies = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("vaga de emprego não encontrada"));

        if(!jobVacancies.getContractor().getId().equals(contractorId)){
            throw new RuntimeException("Você não pode deletar essa vaga");
        }

        jobRepository.deleteById(jobId);
    }
}
