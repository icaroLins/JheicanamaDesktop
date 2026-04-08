package com.br.jheicanama.freelancer.desktop.service.user;

import com.br.jheicanama.freelancer.desktop.dto.login.LoginRequest;
import com.br.jheicanama.freelancer.desktop.dto.login.LoginResponse;
import com.br.jheicanama.freelancer.desktop.dto.user.UserRequest;
import com.br.jheicanama.freelancer.desktop.dto.user.UserResponse;
import com.br.jheicanama.freelancer.desktop.model.user.Candidate;
import com.br.jheicanama.freelancer.desktop.model.user.Contractor;
import com.br.jheicanama.freelancer.desktop.model.user.User;
import com.br.jheicanama.freelancer.desktop.repository.candidate.CandidateRepository;
import com.br.jheicanama.freelancer.desktop.repository.contractor.ContractorRepository;
import com.br.jheicanama.freelancer.desktop.repository.user.UserRepository;
import com.br.jheicanama.freelancer.desktop.section.user.UserSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSection userSection;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ContractorRepository contractorRepository;

    public UserResponse cadastrarUser(UserRequest userRequest) {
        if(userRepository.findByEmail(userRequest.getEmail())!=null) {
            throw new RuntimeException("Email ja existente");
        }

        String passworEncrypted =passwordEncoder.encode(userRequest.getPassword());

        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(passworEncrypted);
        user.setRole(userRequest.getRole());
        user.setPhone(userRequest.getPhone());
        user.setUsername(userRequest.getName());

        User userSaved = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(userSaved.getEmail());
        userResponse.setPhone(userSaved.getPhone());
        userResponse.setRole(userSaved.getRole());

        return userResponse;
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {


        Candidate candidate = candidateRepository.findByEmail(loginRequest.getEmail());

        if (candidate != null) {
            if (!passwordEncoder.matches(loginRequest.getPassword(), candidate.getPassword())) {
                throw new RuntimeException("Senha invalida");
            }

            userSection.login(candidate);
            return buildResponse(candidate);
        }

        Contractor contractor = contractorRepository.findByEmail(loginRequest.getEmail());

        if (contractor != null) {
            if (!passwordEncoder.matches(loginRequest.getPassword(), contractor.getPassword())) {
                throw new RuntimeException("Senha invalida");
            }

            userSection.login(contractor);
            return buildResponse(contractor);
        }

        throw new RuntimeException("Email invalido");
    }

    private LoginResponse buildResponse(User user) {
        LoginResponse response = new LoginResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getUsername());
        response.setRole(user.getRole());
        return response;
    }

    public UserResponse searchUser(UserRequest userRequest) {
        User user = userRepository.findByEmail(userRequest.getEmail());

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());
        userResponse.setUsername(user.getUsername());
        return userResponse;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByEmail(String email) {

        Candidate candidate = candidateRepository.findByEmail(email);
        if (candidate != null) {
            return candidate;
        }

        Contractor contractor = contractorRepository.findByEmail(email);
        if (contractor != null) {
            return contractor;
        }

        throw new RuntimeException("Usuário não encontrado");
    }

}
