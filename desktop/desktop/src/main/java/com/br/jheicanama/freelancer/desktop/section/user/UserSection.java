package com.br.jheicanama.freelancer.desktop.section.user;

import com.br.jheicanama.freelancer.desktop.model.user.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserSection {
    private User userLogged;

    public void login(User user) {
        this.userLogged = user;
    }

    public boolean isLogado() {
        return userLogged != null;
    }

    public void logout() {
        this.userLogged = null;
    }
}
