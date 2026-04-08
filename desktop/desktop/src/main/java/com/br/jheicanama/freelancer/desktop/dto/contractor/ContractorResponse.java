package com.br.jheicanama.freelancer.desktop.dto.contractor;

import com.br.jheicanama.freelancer.desktop.dto.user.UserResponse;
import lombok.Data;

@Data
public class ContractorResponse extends UserResponse {
    private String empresa;
    private String cnpj;
}
