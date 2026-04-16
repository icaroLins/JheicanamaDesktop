package com.br.jheicanama.freelancer.desktop.dto.contractor;

import com.br.jheicanama.freelancer.desktop.dto.user.UserResponse;
import com.br.jheicanama.freelancer.desktop.enums.Role;
import lombok.Data;

@Data
public class ContractorResponse extends UserResponse {
    private String empresa;
    private String cnpj;

    public ContractorResponse(String username, String phone, String email, Role role, String empresa, String cnpj) {
        super(username, phone, email, role);
        this.empresa = empresa;
        this.cnpj = cnpj;
    }
}
