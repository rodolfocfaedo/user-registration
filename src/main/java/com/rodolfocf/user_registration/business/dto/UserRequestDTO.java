package com.rodolfocf.user_registration.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRequestDTO {

    private String name;
    private String email;
}
