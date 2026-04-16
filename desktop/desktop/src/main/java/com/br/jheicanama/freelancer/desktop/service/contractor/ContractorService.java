package com.br.jheicanama.freelancer.desktop.service.contractor;

import com.br.jheicanama.freelancer.desktop.dto.contractor.ContractorRequest;
import com.br.jheicanama.freelancer.desktop.dto.contractor.ContractorResponse;
import com.br.jheicanama.freelancer.desktop.dto.login.LoginRequest;
import com.br.jheicanama.freelancer.desktop.dto.login.LoginResponse;
import com.br.jheicanama.freelancer.desktop.model.user.Contractor;
import com.br.jheicanama.freelancer.desktop.repository.contractor.ContractorRepository;
import com.br.jheicanama.freelancer.desktop.section.contractor.ContractorSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ContractorService {
    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private ContractorSection contractorSection;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ContractorResponse cadastrarUser(ContractorRequest userRequest) {
        if(contractorRepository.findByEmail(userRequest.getEmail())!= null) {
            throw new RuntimeException("Email ja existente");
        }

        String passworEncrypted =passwordEncoder.encode(userRequest.getPassword());

        Contractor user = new Contractor(
                userRequest.getName(), passworEncrypted, userRequest.getEmail(), userRequest.getPhone(),
                userRequest.getRole(), userRequest.getEmpresa(), userRequest.getCnpj()
                );

        Contractor userSaved = contractorRepository.save(user);
        ContractorResponse userResponse = new ContractorResponse(userSaved.getUsername(), userSaved.getPhone(),
                userSaved.getEmail(), userSaved.getRole(), userSaved.getEmpresa(), userSaved.getCnpj());

        return userResponse;
    }

    public ContractorResponse searchUser(ContractorRequest userRequest) {
        Contractor user = contractorRepository.findByEmail(userRequest.getEmail());

        ContractorResponse userResponse = new ContractorResponse(user.getUsername(),user.getPhone(),user.getEmail(),
                user.getRole(),user.getEmpresa(),user.getCnpj());
        return userResponse;
    }
}
