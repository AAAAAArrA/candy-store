package com.example.chocolateshop.repositories;

import com.example.chocolateshop.dto.UserDTO;
import com.example.chocolateshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByFullName(String fullName);

}
