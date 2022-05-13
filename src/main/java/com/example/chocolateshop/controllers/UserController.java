package com.example.chocolateshop.controllers;


import com.example.chocolateshop.dto.UserDTO;
import com.example.chocolateshop.services.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final CustomUserService userService;
    public UserController(CustomUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new  UserDTO());
        return "users/userForm";
    }

    @PostMapping("/new")
    public String saveUser(UserDTO userDTO, Model model){
        if(userService.save(userDTO)){
            return "redirect:/";
        }else {
            model.addAttribute("user", userDTO);
            return "users/userForm";
        }
    }

}
