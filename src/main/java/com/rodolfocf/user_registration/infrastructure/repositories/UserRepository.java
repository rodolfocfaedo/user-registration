package com.rodolfocf.user_registration.infrastructure.repositories;

import com.rodolfocf.user_registration.business.dto.UserRequestDTO;
import com.rodolfocf.user_registration.infrastructure.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    User saveUser(UserRequestDTO userRequestDTO);


}
