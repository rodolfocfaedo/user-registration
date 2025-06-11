package com.rodolfocf.user_registration.business;

import com.rodolfocf.user_registration.business.converter.UserConverter;
import com.rodolfocf.user_registration.business.dto.UserRequestDTO;
import com.rodolfocf.user_registration.business.dto.UserResponseDTO;
import com.rodolfocf.user_registration.infrastructure.entities.User;
import com.rodolfocf.user_registration.infrastructure.exception.EmailAlreadyRegisteredException;
import com.rodolfocf.user_registration.infrastructure.exception.EmailNotFoundException;
import com.rodolfocf.user_registration.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    String email;

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new EmailAlreadyRegisteredException("Email \"" + userRequestDTO.getEmail() + "\" is already registered.");
        }
        User convertedUser = userConverter.fromUserRequestDTOtoUserEntity(userRequestDTO);
        return userConverter.fromUserEntitytoUserResponseDTO(userRepository.saveAndFlush(convertedUser));
    }

    public UserResponseDTO searchUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new EmailNotFoundException("Email \"" + email + "\" not found."));
        return userConverter.fromUserEntitytoUserResponseDTO(user);

    }


}

