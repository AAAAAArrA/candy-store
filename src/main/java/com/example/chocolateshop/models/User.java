package com.example.chocolateshop.models;

import com.example.chocolateshop.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "bucket_ID")
    private Bucket bucket;

}
