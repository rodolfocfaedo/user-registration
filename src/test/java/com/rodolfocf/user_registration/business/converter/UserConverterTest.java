package com.rodolfocf.user_registration.business.converter;

import com.rodolfocf.user_registration.business.dto.UserRequestDTO;
import com.rodolfocf.user_registration.business.dto.UserRequestDTOFixture;
import com.rodolfocf.user_registration.business.dto.UserResponseDTO;
import com.rodolfocf.user_registration.business.dto.UserResponseDTOFixture;
import com.rodolfocf.user_registration.infrastructure.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserConverterTest {

    @InjectMocks
    UserConverter userConverter;

    User user;
    UserRequestDTO userRequestDTO;
    UserResponseDTO userResponseDTO;

    @BeforeEach
    public void setup() {

        user = User.builder()
                .id(12345)
                .name("Afonso")
                .email("afonso@email.com")
                .build();

        userRequestDTO = UserRequestDTOFixture.build("Afonso", "afonso@email.com");

        userResponseDTO = UserResponseDTOFixture.build(12345, "Afonso", "afonso@email.com");
    }

    @Test
    @DisplayName("Should convert a requestDTO in an entity")
    void shouldConvertUserRequestDTOInAUserEntity(){
        User convertedUser = userConverter.fromUserRequestDTOtoUserEntity(userRequestDTO);
        assertThat(convertedUser.getEmail()).isEqualTo(userRequestDTO.getEmail());
        assertThat(convertedUser.getName()).isEqualTo(userRequestDTO.getName());
    }

    @Test
    @DisplayName("Should convert an entity in a responseDTO")
    void shouldConvertAUserEntityInAUserResponseDTO(){
        UserResponseDTO convertedUser = userConverter.fromUserEntitytoUserResponseDTO(user);
        assertThat(convertedUser.getId()).isEqualTo(user.getId());
        assertThat(convertedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(convertedUser.getName()).isEqualTo(user.getName());
    }
}