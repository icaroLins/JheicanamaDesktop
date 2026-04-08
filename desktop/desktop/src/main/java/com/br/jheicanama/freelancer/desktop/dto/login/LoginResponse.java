package com.br.jheicanama.freelancer.desktop.dto.login;

import com.br.jheicanama.freelancer.desktop.enums.Role;
import lombok.Data;

@Data
public class LoginResponse {
    private Long id;
    private String email;
    private String name;
    private Role role;
}
