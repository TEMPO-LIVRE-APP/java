package com.tempolivre.api.repository;

import com.tempolivre.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByEmail(String email);

    UserDetails findByUsername(String username);

}
