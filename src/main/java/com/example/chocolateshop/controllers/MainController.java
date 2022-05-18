package com.example.chocolateshop.controllers;


import com.example.chocolateshop.repositories.BucketRepository;
import com.example.chocolateshop.services.SessionObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class MainController {
    private final SessionObjectHolder objectHolder;
    private final BucketRepository bucketRepository;
    public MainController(SessionObjectHolder objectHolder, BucketRepository bucketRepository) {
        this.objectHolder = objectHolder;
        this.bucketRepository = bucketRepository;
    }

    @RequestMapping({"", "/"})
    public String index(Model model, HttpSession httpSession){
        model.addAttribute("amountClicks", objectHolder.getAmountClicks());
        if(httpSession.getAttribute("myID") == null){
            String uuid = UUID.randomUUID().toString();
            httpSession.setAttribute("myID", uuid);
            System.out.println("Generated uuid -> " + uuid);
        }
        model.addAttribute("uuid", httpSession.getAttribute("myID"));
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
