package com.br.jheicanama.freelancer.desktop.dto.candidate;

import com.br.jheicanama.freelancer.desktop.dto.user.UserResponse;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CandidateResponse extends UserResponse {
    private LocalDate birthDate;
    private String cpf;
}
