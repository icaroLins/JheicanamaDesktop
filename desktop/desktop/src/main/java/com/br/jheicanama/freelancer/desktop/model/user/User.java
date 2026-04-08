package com.br.jheicanama.freelancer.desktop.model.user;

import com.br.jheicanama.freelancer.desktop.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
}
