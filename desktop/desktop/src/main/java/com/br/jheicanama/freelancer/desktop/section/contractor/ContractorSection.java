package com.br.jheicanama.freelancer.desktop.section.contractor;

import com.br.jheicanama.freelancer.desktop.model.user.Contractor;
import com.br.jheicanama.freelancer.desktop.section.user.UserSection;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ContractorSection extends UserSection {
    private Contractor contractorLogged;

    public void login(Contractor user) {
        this.contractorLogged = user;
    }

    public boolean isLogado() {
        return contractorLogged != null;
    }

    public void logout() {
        this.contractorLogged = null;
    }

}
