package com.br.jheicanama.freelancer.desktop.model.user;

import com.br.jheicanama.freelancer.desktop.enums.Role;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Candidate extends User{
    private LocalDate dateOfBirth;
    private String cpf;

    public Candidate(String username, String password, String email, String phone, Role role, LocalDate dateOfBirth, String cpf) {
        super(username, password, email, phone, role);
        this.dateOfBirth = dateOfBirth;
        this.cpf = cpf;
    }

    public Candidate() {

    }
}
