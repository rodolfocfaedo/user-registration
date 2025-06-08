package com.rodolfocf.user_registration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodolfocf.user_registration.business.UserService;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;
    @Mock
    UserRepository userRepository;
    @Mock
    UserConverter userConverter;
    @Mock
    UserService userService;
    MockMvc mockMvc;
    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
    String json;
    final ObjectMapper objectMapper = new ObjectMapper();
    User user;
    UserRequestDTO userRequestDTO;
    UserResponseDTO userResponseDTO;

    @BeforeEach
    public void setup() throws JsonProcessingException {

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(globalExceptionHandler)
                .alwaysDo(print())
                .build();

        userRequestDTO = UserRequestDTOFixture.build("Afonso", "afonso@email.com");

        userResponseDTO = UserResponseDTOFixture.build(12345, "Afonso", "afonso@email.com");

        json = objectMapper.writeValueAsString(userRequestDTO);

    }

    @Test
    @DisplayName("POST /user/register - Should save user data successfully")
    void shouldSaveUserDataSuccessfully() throws Exception {

        //GIVEN
        String url = "/user/register";
        when(userService.saveUser(any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        //WHEN
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isCreated());
        //THEN
        verify(userService, times(1)).saveUser(any(UserRequestDTO.class));
    }


    @Test
    @DisplayName("POST /user/register - Should throw EmailAlreadyExistsException if email is already exists")
    void shouldThrowEmailAlreadyExistsExceptionIfEmailAlreadyExists() throws Exception {

        //GIVEN
        String url = "/user/register";

        when(userService.saveUser(any(UserRequestDTO.class)))
                .thenThrow(new EmailAlreadyRegisteredException("Email " + userRequestDTO.getEmail() + " is already registered"));

        //WHEN
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict());

        //THEN
        verify(userService, times(1)).saveUser(any(UserRequestDTO.class));


    }
}

