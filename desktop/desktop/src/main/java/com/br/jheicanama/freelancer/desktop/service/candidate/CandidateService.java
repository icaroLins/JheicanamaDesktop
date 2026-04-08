package com.br.jheicanama.freelancer.desktop.service.candidate;

import com.br.jheicanama.freelancer.desktop.dto.candidate.CandidateRequest;
import com.br.jheicanama.freelancer.desktop.dto.candidate.CandidateResponse;
import com.br.jheicanama.freelancer.desktop.model.user.Candidate;
import com.br.jheicanama.freelancer.desktop.repository.candidate.CandidateRepository;
import com.br.jheicanama.freelancer.desktop.section.candidate.CandidateSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateSection candidateSection;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateResponse cadastrarUser(CandidateRequest userRequest) {
        if(candidateRepository.findByEmail(userRequest.getEmail())!=null) {
            throw new RuntimeException("Email ja existente");
        }

        String passworEncrypted =passwordEncoder.encode(userRequest.getPassword());

        Candidate user = new Candidate();
        user.setEmail(userRequest.getEmail());
        user.setPassword(passworEncrypted);
        user.setRole(userRequest.getRole());
        user.setPhone(userRequest.getPhone());
        user.setUsername(userRequest.getName());
        user.setCpf(userRequest.getCpf());
        user.setDateOfBirth(userRequest.getBirthDate());

        Candidate userSaved = candidateRepository.save(user);
        CandidateResponse userResponse = new CandidateResponse();
        userResponse.setEmail(userSaved.getEmail());
        userResponse.setPhone(userSaved.getPhone());
        userResponse.setRole(userSaved.getRole());
        userResponse.setCpf(userSaved.getCpf());
        userResponse.setBirthDate(userSaved.getDateOfBirth());

        return userResponse;
    }

    public CandidateResponse searchUser(CandidateRequest userRequest) {
        Candidate user = candidateRepository.findByEmail(userRequest.getEmail());

        CandidateResponse userResponse = new CandidateResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());
        userResponse.setUsername(user.getUsername());
        userResponse.setCpf(user.getCpf());
        userResponse.setBirthDate(user.getDateOfBirth());
        return userResponse;
    }
}
