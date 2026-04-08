package com.br.jheicanama.freelancer.desktop.dto.contractor;

import com.br.jheicanama.freelancer.desktop.dto.user.UserRequest;
import lombok.Data;

@Data
public class ContractorRequest extends UserRequest {
    private String empresa;
    private String cnpj;
}
