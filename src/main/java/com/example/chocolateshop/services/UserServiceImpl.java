package com.example.chocolateshop.services;


import com.example.chocolateshop.dto.UserDTO;
import com.example.chocolateshop.enums.Role;
import com.example.chocolateshop.models.User;
import com.example.chocolateshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean saveClient(UserDTO userDTO) {
        if(!Objects.equals(userDTO.getPassword(), userDTO.getPasswordMatching())){
            throw new RuntimeException("Password is not equals");
        }
        User user = User.builder()
                .fullName(userDTO.getUserName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.CLIENT)
                .build();
        userRepository.save(user);
        return true;
    }

    public List<User> allUsers(){
        return userRepository.findAll();
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
