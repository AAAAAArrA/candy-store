package com.example.chocolateshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String userName;
    private String email;
    private String password;
    private String passwordMatching;
    private String role;
}
