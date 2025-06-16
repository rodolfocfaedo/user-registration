package com.rodolfocf.user_registration.business;

import com.rodolfocf.user_registration.business.converter.UserConverter;
import com.rodolfocf.user_registration.business.dto.UserRequestDTO;
import com.rodolfocf.user_registration.business.dto.UserRequestDTOFixture;
import com.rodolfocf.user_registration.business.dto.UserResponseDTO;
import com.rodolfocf.user_registration.business.dto.UserResponseDTOFixture;
import com.rodolfocf.user_registration.infrastructure.entities.User;
import com.rodolfocf.user_registration.infrastructure.exception.EmailAlreadyRegisteredException;
import com.rodolfocf.user_registration.infrastructure.exception.EmailNotFoundException;
import com.rodolfocf.user_registration.infrastructure.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;

    private User user;
    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;
    String email;


    @BeforeEach
    public void setup() {

        // Your existing setup
        user = User.builder()
                .id(12345)
                .name("Afonso")
                .email("afonso@email.com")
                .build();

        userRequestDTO = UserRequestDTOFixture.build("Afonso", "afonso@email.com");
        userResponseDTO = UserResponseDTOFixture.build(12345, "Afonso", "afonso@email.com");
        email = "afonso@email.com";

    }


    @Test
    @DisplayName("Should save user successfully")
    void shouldSaveUserSuccessfully() {
        // GIVEN
        //primeiro vai no repositório verificar se o email já existe(nesse caso é falso para o teste)
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
    @DisplayName("Should throw EmailAlreadyExistsException if email is already exists")
    void shouldThrowEmailAlreadyExistsExceptionIfEmailAlreadyExists() {
        //GIVEN
        //primeiro vai no repositório verificar se o email já existe(nesse caso é true para o teste)
        when(userRepository.existsByEmail(userRequestDTO.getEmail())).thenReturn(true);

        //WHEN and THEN
        Assertions.assertThatThrownBy(() -> userService.saveUser(userRequestDTO))
                .isInstanceOf(EmailAlreadyRegisteredException.class);


    }


    @Test
    @DisplayName("Should find user by email successfully")
    void shouldSearchUserByEmailSuccessfully() {
        //GIVEN
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userConverter.fromUserEntitytoUserResponseDTO(user)).thenReturn(userResponseDTO);

        //WHEN
        UserResponseDTO responseDTO = userService.searchUserByEmail(email);

        //Then
        Assertions.assertThat(responseDTO).isEqualTo(userResponseDTO);

    }

    @Test
    @DisplayName("Should throw EmailNotFoundException if email is not found")
    void shouldThrowEmailNotFoundExceptionIfEmailWasNotFound() {
        //GIVEN
        //primeiro vai no repositório verificar se o email já existe(nesse caso é false para o teste)
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        //WHEN and THEN
        Assertions.assertThatThrownBy(() -> userService.searchUserByEmail(email))
                .isInstanceOf(EmailNotFoundException.class);

    }

    @Test
    @DisplayName("Should update user by email successfully")
    void shouldUpdateUserByEmailSuccessfully() {
        //GIVEN
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userConverter.fromUserEntitytoUserResponseDTO(user)).thenReturn(userResponseDTO);

        //WHEN
        UserResponseDTO responseDTO = userService.updateUserByEmail(email);

        //Then
        Assertions.assertThat(responseDTO).isEqualTo(userResponseDTO);

    }

    @Test
    @DisplayName("Should throw EmailNotFoundException if email is not found during updating")
    void shouldThrowEmailNotFoundExceptionIfEmailWasNotFoundDuringUpdate() {
        //GIVEN
        //primeiro vai no repositório verificar se o email já existe(nesse caso é false para o teste)
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        //WHEN and THEN
        Assertions.assertThatThrownBy(() -> userService.updateUserByEmail(email))
                .isInstanceOf(EmailNotFoundException.class);
    }

        @Test
        @DisplayName("Should delete user by email successfully")
        void shouldDeleteUserByEmailSuccessfully () {
            //GIVEN
            when(userRepository.existsByEmail(email)).thenReturn(true);

            userService.deleteUserByEmail(email);

            //WHEN and THEN
            verify(userRepository).deleteByEmail(email);

        }

        @Test
        @DisplayName("Should throw EmailNotFoundException if email is not found during delete")
        void shouldThrowEmailNotFoundExceptionIfEmailWasNotFoundDuringDelete () {
            //GIVEN
            //primeiro vai no repositório verificar se o email já existe(nesse caso é false para o teste)
            when(userRepository.existsByEmail(email)).thenReturn(false);

            //WHEN and THEN
            Assertions.assertThatThrownBy(() -> userService.deleteUserByEmail(email))
                    .isInstanceOf(EmailNotFoundException.class);

        }
    }