package com.br.jheicanama.freelancer.desktop.section.candidate;

import com.br.jheicanama.freelancer.desktop.model.user.Candidate;
import com.br.jheicanama.freelancer.desktop.section.user.UserSection;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CandidateSection extends UserSection {
    private Candidate userLogged;

    public void login(Candidate user) {
        this.userLogged = user;
    }


    public boolean isLogado() {
        return userLogged != null;
    }


    public void logout() {
        this.userLogged = null;
    }
}
