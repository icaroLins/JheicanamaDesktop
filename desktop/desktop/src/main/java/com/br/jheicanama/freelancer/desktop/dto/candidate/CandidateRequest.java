package com.br.jheicanama.freelancer.desktop.dto.candidate;

import com.br.jheicanama.freelancer.desktop.dto.user.UserRequest;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CandidateRequest extends UserRequest {
    private String cpf;
    private LocalDate birthDate;
}
