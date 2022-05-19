package com.example.chocolateshop.controllers;


import com.example.chocolateshop.dto.BucketDTO;
import com.example.chocolateshop.services.BucketService;
import com.example.chocolateshop.services.ProductService;
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
    private final ProductService productService;
    public BucketController(BucketService bucketService, ProductService productService) {
        this.bucketService = bucketService;
        this.productService = productService;
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

}
