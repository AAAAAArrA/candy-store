package com.example.chocolateshop.controllers;


import com.example.chocolateshop.dto.UserDTO;
import com.example.chocolateshop.models.User;
import com.example.chocolateshop.services.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {
    private final CustomUserService customUserService;

    public UserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @GetMapping
    public String userList(Model model){
        model.addAttribute("user", customUserService.allUsers());
        return "users/userList";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new  UserDTO());
        return "users/userForm";
    }

    @PostMapping("/new")
    public String saveUser(UserDTO userDTO, Model model){
        if(customUserService.save(userDTO)){
            return "redirect:/users";
        }else {
            model.addAttribute("user", userDTO);
            return "users/userForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        customUserService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal){
        if(principal == null){
            throw new RuntimeException("You are not authorize");
        }
        User user = customUserService.findUser(principal.getName());

        UserDTO userDTO = UserDTO.builder()
                .userName(user.getFullName())
                .email(user.getEmail())
                .build();
        model.addAttribute("user", userDTO);
        return "users/profiles";
    }

    @PostMapping("/profile")
    public String updateUserProfile(UserDTO dto, Model model, Principal principal){
        if(principal == null || !Objects.equals(principal.getName(), dto.getUserName())){
            throw new RuntimeException("You are not authorize");
        }
        if(dto.getPassword() != null
                && !dto.getPassword().isEmpty()){
//                && !Objects.equals(dto.getPassword(), dto.getPasswordMatching())) {
            model.addAttribute("user", dto);
            return "users/profiles";
        }
        customUserService.updateProfile(dto);
        return "redirect:/users/profile";
    }
}
