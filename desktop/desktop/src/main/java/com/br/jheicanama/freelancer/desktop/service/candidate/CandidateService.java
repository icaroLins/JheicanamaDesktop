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

        Candidate user = new Candidate(
                userRequest.getName(),passworEncrypted,userRequest.getEmail(), userRequest.getPhone(),
                userRequest.getRole(), userRequest.getBirthDate(), userRequest.getCpf()
                );

        Candidate userSaved = candidateRepository.save(user);
        CandidateResponse userResponse = new CandidateResponse(userSaved.getUsername(),userSaved.getPhone(),
                userSaved.getEmail(), userSaved.getRole(), userSaved.getDateOfBirth(), userSaved.getCpf()
                );

        return userResponse;
    }

    public CandidateResponse searchUser(CandidateRequest userRequest) {
        Candidate user = candidateRepository.findByEmail(userRequest.getEmail());

        CandidateResponse userResponse = new CandidateResponse(user.getUsername(), user.getPhone(), user.getEmail(),
                user.getRole(), user.getDateOfBirth(), user.getCpf());
        return userResponse;
    }
}
