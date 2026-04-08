package com.br.jheicanama.freelancer.desktop.repository.candidate;

import com.br.jheicanama.freelancer.desktop.model.user.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    Candidate findByEmail(String email);
}
