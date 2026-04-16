package com.br.jheicanama.freelancer.desktop.model.user;

import com.br.jheicanama.freelancer.desktop.enums.Role;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contractor extends User{
    private String empresa;
    private String cnpj;

    public Contractor(String username, String password, String email, String phone, Role role, String empresa, String cnpj) {
        super(username, password, email, phone, role);
        this.empresa = empresa;
        this.cnpj = cnpj;
    }

    public Contractor() {
    }
}
