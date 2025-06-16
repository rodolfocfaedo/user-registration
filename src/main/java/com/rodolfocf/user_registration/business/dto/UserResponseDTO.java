package com.rodolfocf.user_registration.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Integer id;
    private String name;
    private String email;
}
