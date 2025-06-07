package com.rodolfocf.user_registration.business.dto;

public class UserRequestDTOFixture {

    public static UserRequestDTO build(String name, String email ){
        return new UserRequestDTO(name, email);
    }
}
