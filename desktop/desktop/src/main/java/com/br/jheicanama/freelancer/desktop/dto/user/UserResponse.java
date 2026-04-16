package com.br.jheicanama.freelancer.desktop.dto.user;

import com.br.jheicanama.freelancer.desktop.enums.Role;
import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String phone;
    private String email;
    private Role role;

    public UserResponse(String username, String phone, String email, Role role) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }
}
