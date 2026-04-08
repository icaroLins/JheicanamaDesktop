package com.br.jheicanama.freelancer.desktop.repository.contractor;

import com.br.jheicanama.freelancer.desktop.model.user.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor,Long> {
    Contractor findByEmail(String email);
}
