package com.rodolfocf.user_registration.controller;

import com.rodolfocf.user_registration.business.UserService;
import com.rodolfocf.user_registration.business.dto.UserRequestDTO;
import com.rodolfocf.user_registration.business.dto.UserResponseDTO;
import com.rodolfocf.user_registration.infrastructure.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User registration, search and delete")
public class UserController {

    
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Create new user")
    @ApiResponse(responseCode = "201", description = "User saved successfully")
    @ApiResponse(responseCode = "400", description = "User already registered")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO savedUser = userService.saveUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/search")
    @Operation(summary = "Search user", description = "Search user")
    @ApiResponse(responseCode = "200", description = "User found successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<UserResponseDTO> searchUserByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.searchUserByEmail(email));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete user", description = "Delete user")
    @ApiResponse(responseCode = "200", description = "User delete successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<Void> deleteUserByEmail(@RequestParam("email") String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
