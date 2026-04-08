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

        Contractor user = new Contractor();
        user.setEmail(userRequest.getEmail());
        user.setPassword(passworEncrypted);
        user.setRole(userRequest.getRole());
        user.setPhone(userRequest.getPhone());
        user.setUsername(userRequest.getName());
        user.setCnpj(userRequest.getCnpj());
        user.setEmpresa(userRequest.getEmpresa());

        Contractor userSaved = contractorRepository.save(user);
        ContractorResponse userResponse = new ContractorResponse();
        userResponse.setEmail(userSaved.getEmail());
        userResponse.setPhone(userSaved.getPhone());
        userResponse.setRole(userSaved.getRole());
        userResponse.setCnpj(userSaved.getCnpj());
        userResponse.setEmpresa(userSaved.getEmpresa());

        return userResponse;
    }

    public ContractorResponse searchUser(ContractorRequest userRequest) {
        Contractor user = contractorRepository.findByEmail(userRequest.getEmail());

        ContractorResponse userResponse = new ContractorResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());
        userResponse.setUsername(user.getUsername());
        userResponse.setCnpj(user.getCnpj());
        userResponse.setEmpresa(user.getEmpresa());
        return userResponse;
    }
}
