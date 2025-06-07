package com.rodolfocf.user_registration.business;

import com.rodolfocf.user_registration.business.converter.UserConverter;
import com.rodolfocf.user_registration.business.dto.UserRequestDTO;
import com.rodolfocf.user_registration.business.dto.UserRequestDTOFixture;
import com.rodolfocf.user_registration.business.dto.UserResponseDTO;
import com.rodolfocf.user_registration.business.dto.UserResponseDTOFixture;
import com.rodolfocf.user_registration.infrastructure.entities.User;
import com.rodolfocf.user_registration.infrastructure.exception.EmailAlreadyRegisteredException;
import com.rodolfocf.user_registration.infrastructure.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    private UserConverter userConverter;
    private UserService userService;

    // Your test data fields
    private User user;
    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    public void setup() {
        // Initialize the real converter
        userConverter = new UserConverter();

        // Manually inject dependencies into UserService
        userService = new UserService(userRepository, userConverter);

        // Your existing setup
        user = User.builder()
                .id(12345)
                .name("Afonso")
                .email("afonso@email.com")
                .build();

        userRequestDTO = UserRequestDTOFixture.build("Afonso", "afonso@email.com");
        userResponseDTO = UserResponseDTOFixture.build(12345, "Afonso", "afonso@email.com");
    }
//recebe um dto(request); converte em entity; salva no banco de dados; converte em dto(response)


    @Test
    @DisplayName("Should save user successfully")
    void shouldSaveUserSuccessfully() {
        // GIVEN
        when(userRepository.existsByEmail(userRequestDTO.getEmail())).thenReturn(false);
        when(userConverter.fromUserRequestDTOtoUserEntity(userRequestDTO)).thenReturn(user);
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        when(userConverter.fromUserEntitytoUserResponseDTO(user)).thenReturn(userResponseDTO);

        // WHEN
        UserResponseDTO savedUserDTO = userService.saveUser(userRequestDTO);

        // THEN
        assertThat(savedUserDTO).isEqualTo(userResponseDTO);

    }


    @Test
    void shouldThrowEmailAlreadyExistsExceptionIfEmailAlreadyExists() {
        //GIVEN
        when(userRepository.existsByEmail(userRequestDTO.getEmail())).thenReturn(true);

        //WHEN & THEN
        assertThatThrownBy(() -> userService.saveUser(userRequestDTO))
                .isInstanceOf(EmailAlreadyRegisteredException.class)
                .hasMessageContaining("Email " + userRequestDTO.getEmail() + " is already registered");
    }
}