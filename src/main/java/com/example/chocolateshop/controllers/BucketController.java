package com.example.chocolateshop.controllers;


import com.example.chocolateshop.dto.BucketDTO;
import com.example.chocolateshop.repositories.BucketRepository;
import com.example.chocolateshop.services.BucketService;
import com.example.chocolateshop.services.ChocolateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/bucket")
public class BucketController {
    private final BucketService bucketService;
    private final BucketRepository  bucketRepository;
    private final ChocolateService chocolateService;
    public BucketController(BucketService bucketService, BucketRepository bucketRepository, ChocolateService chocolateService) {
        this.bucketService = bucketService;
        this.bucketRepository = bucketRepository;
        this.chocolateService = chocolateService;
    }


    @GetMapping
    public String aboutBucket(Model model, Principal principal){
        if(principal == null){
            model.addAttribute("bucket", new BucketDTO());
        }else{
            BucketDTO bucketDTO = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDTO);
        }
        return "bucket";
    }

    @PostMapping
    public String commitBucket(Principal principal){
        if(principal != null){
            bucketService.commitBucketToOrder(principal.getName());
        }
        return "redirect:/bucket";
    }
    @GetMapping("/report-1")
    public String bucket(Model model){
        model.addAttribute("firstReport", bucketRepository.findAll());
        model.addAttribute("chocolates", chocolateService.chocolateList());
        return "report-1";

    }
}
