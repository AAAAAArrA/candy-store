package com.example.chocolateshop.services;


import com.example.chocolateshop.dto.UserDTO;
import com.example.chocolateshop.enums.Role;
import com.example.chocolateshop.models.User;
import com.example.chocolateshop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean save(UserDTO userDTO) {
        if(!Objects.equals(userDTO.getPassword(), userDTO.getPasswordMatching())){
            throw new RuntimeException("Password is not equals");
        }
        User user = User.builder()
                .fullName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .role(Role.CLIENT)
                .build();
        userRepository.save(user);
        return true;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByFullName(username);
//        if(user == null){
//            throw new UsernameNotFoundException("User not found with name " + username);
//        }
//        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority(user.getRole().name()));
//        return new org.springframework.security.core.userdetails.User(
//                user.getFullName(),
//                user.getPassword(),
//                roles
//        );
//    }
}
