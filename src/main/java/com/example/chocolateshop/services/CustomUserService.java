package com.example.chocolateshop.services;

import com.example.chocolateshop.dto.UserDTO;
import com.example.chocolateshop.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomUserService {
    boolean save(UserDTO userDTO);
    void save(User user);
    boolean saveManager(UserDTO userDTO);
    boolean saveAdmin(UserDTO userDTO);
    List<User> allUsers();
    void delete(Long id);
    User findUser(String userName);
    UserDTO toDTO(User user);
    void updateProfile(UserDTO dto);
}
