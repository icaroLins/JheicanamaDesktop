package com.br.jheicanama.freelancer.desktop.dto.user;

import com.br.jheicanama.freelancer.desktop.enums.Role;
import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String password;
    private String name;
    private String phone;
    private Role role;
}
