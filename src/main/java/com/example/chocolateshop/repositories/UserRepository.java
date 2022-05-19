package com.example.chocolateshop.repositories;

import com.example.chocolateshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFullName(String fullName);

}
