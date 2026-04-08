package com.br.jheicanama.freelancer.desktop.repository.user;

import com.br.jheicanama.freelancer.desktop.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
