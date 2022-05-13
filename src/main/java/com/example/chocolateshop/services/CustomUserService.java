package com.example.chocolateshop.services;

import com.example.chocolateshop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserService extends UserDetailsService { //security
    boolean save(UserDTO userDTO);
}
