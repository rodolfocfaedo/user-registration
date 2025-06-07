package com.rodolfocf.user_registration.business.converter;

import com.rodolfocf.user_registration.business.dto.UserRequestDTO;
import com.rodolfocf.user_registration.business.dto.UserResponseDTO;
import com.rodolfocf.user_registration.infrastructure.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User fromUserRequestDTOtoUserEntity(UserRequestDTO userRequestDTO) {
        return  User.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .build();
    }

    public  UserResponseDTO fromUserEntitytoUserResponseDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
