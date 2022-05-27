package com.example.chocolateshop.controllers;


import com.example.chocolateshop.repositories.BucketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    private final BucketRepository bucketRepository;

    public MainController(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    @RequestMapping({"", "/"})
    public String index(Model model, HttpSession httpSession){
        return "index";
    }


    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "login";
    }


}
