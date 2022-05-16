package com.example.chocolateshop.controllers;


import com.example.chocolateshop.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String allChocolates(Model model){
        model.addAttribute("chocolateList", productService.getAll());
        return "candy/chocolateList";
    }

    @GetMapping("{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal){
        if(principal == null){
//            return "redirect:/products";
            return "redirect:/candy";
        }
        productService.addToUserBucket(id, principal.getName());
//        return "redirect:/products";
        return "redirect:/candy";
    }
}
