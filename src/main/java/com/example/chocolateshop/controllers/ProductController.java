package com.example.chocolateshop.controllers;


import com.example.chocolateshop.models.Product;
import com.example.chocolateshop.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping("/{id}")
    public String chocolateInfo(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findProduct(id));
        return "candy/oneProduct";
    }
    @GetMapping("/add-product")
    public String createProduct(Model model){
        model.addAttribute("product", new Product());
        return "candy/productForm";
    }
    @PostMapping("/save")
    public String saveProduct(@RequestParam("file")MultipartFile file,
                              Product product) throws Exception{
        productService.saveProductToDB(product, file);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findProduct(id));
        return "candy/productForm";
    }

    @GetMapping("{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal){
        if(principal == null){
            return "redirect:/products";
        }
        productService.addToUserBucket(id, principal.getName());
        return "redirect:/products";
    }

}
