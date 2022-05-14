package com.example.chocolateshop.controllers;


import com.example.chocolateshop.dto.UserDTO;
import com.example.chocolateshop.repositories.UserRepository;
import com.example.chocolateshop.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userRepository;

    public UserController(UserServiceImpl userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String userList(Model model){
        model.addAttribute("user", userRepository.allUsers());
        return "users/userList";

    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new  UserDTO());
        return "users/userForm";
    }

    @PostMapping("/new")
    public String saveUser(UserDTO userDTO, Model model){
        if(userRepository.saveClient(userDTO)){
            return "redirect:/users";
        }else {
            model.addAttribute("user", userDTO);
            return "users/userForm";
        }
    }

}
