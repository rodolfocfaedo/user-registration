package com.rodolfocf.user_registration.business.dto;

public class UserResponseDTOFixture {

    public static UserResponseDTO build(Integer id, String name, String email ){
        return new UserResponseDTO(id, name, email);
    }

}
