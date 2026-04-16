package com.br.jheicanama.freelancer.desktop.dto.candidate;

import com.br.jheicanama.freelancer.desktop.dto.user.UserResponse;
import com.br.jheicanama.freelancer.desktop.enums.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CandidateResponse extends UserResponse {
    private LocalDate birthDate;
    private String cpf;

    public CandidateResponse(String username, String phone, String email, Role role, LocalDate birthDate, String cpf) {
        super(username, phone, email, role);
        this.birthDate = birthDate;
        this.cpf = cpf;
    }
}
