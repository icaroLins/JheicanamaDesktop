package com.br.jheicanama.freelancer.desktop.dto.user;

import com.br.jheicanama.freelancer.desktop.enums.Role;
import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String phone;
    private String email;
    private Role role;
}
