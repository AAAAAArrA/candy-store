package com.example.chocolateshop.services;


import com.example.chocolateshop.dto.UserDTO;
import com.example.chocolateshop.enums.Role;
import com.example.chocolateshop.models.User;
import com.example.chocolateshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public boolean save(UserDTO userDTO) {
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
    public boolean saveManager(UserDTO userDTO) {
        if(!Objects.equals(userDTO.getPassword(), userDTO.getPasswordMatching())){
            throw new RuntimeException("Password is not equals");
        }
        User user = User.builder()
                .fullName(userDTO.getUserName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.MANAGER)
                .build();
        userRepository.save(user);
        return true;
    }
    public boolean saveAdmin(UserDTO userDTO) {
        if(!Objects.equals(userDTO.getPassword(), userDTO.getPasswordMatching())){
            throw new RuntimeException("Password is not equals");
        }
        User user = User.builder()
                .fullName(userDTO.getUserName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        return true;
    }
    public List<User> allUsers(){
        return userRepository.findAll();
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public User findUser(String userName){

        return userRepository.findByFullName(userName);
    }

    private UserDTO toDTO(User user){
        return UserDTO.builder()
                .userName(user.getFullName())
                .email(user.getEmail())
                .build();
    }

    @Transactional
    public void updateProfile(UserDTO dto){
        User savedUser = userRepository.findByFullName(dto.getUserName());
        if(savedUser == null){
            throw new RuntimeException("User not found " + dto.getUserName());
        }
        boolean isChanged = false;

        if(dto.getPassword() != null && !dto.getPassword().isEmpty()){
            savedUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            isChanged = true;
        }

        if(!Objects.equals(dto.getEmail(), savedUser.getEmail())){
            savedUser.setEmail(dto.getEmail());
            isChanged = true;
        }

        if(isChanged){
            userRepository.save(savedUser);
        }
    }

    public void save(User user){
        userRepository.save(user);
    }


}
