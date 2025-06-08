package com.rodolfocf.user_registration.controller;

import com.rodolfocf.user_registration.business.UserService;
import com.rodolfocf.user_registration.business.dto.UserRequestDTO;
import com.rodolfocf.user_registration.business.dto.UserResponseDTO;
import com.rodolfocf.user_registration.infrastructure.entities.User;
import com.rodolfocf.user_registration.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO savedUser = userService.saveUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
