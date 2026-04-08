package com.br.jheicanama.freelancer.desktop.model.user;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contractor extends User{
    private String empresa;
    private String cnpj;
}
