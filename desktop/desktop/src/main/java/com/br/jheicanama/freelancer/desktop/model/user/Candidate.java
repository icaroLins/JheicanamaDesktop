package com.br.jheicanama.freelancer.desktop.model.user;

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

}
