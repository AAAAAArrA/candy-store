package com.example.chocolateshop.controllers;


import com.example.chocolateshop.models.Chocolate;
import com.example.chocolateshop.services.ChocolateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/candy")
public class ChocolateController {
    private  final ChocolateService chocolateService;
    public ChocolateController(ChocolateService chocolateService) {
        this.chocolateService = chocolateService;
    }
    @GetMapping
    public String allChocolates(Model model){
        model.addAttribute("chocolateList", chocolateService.chocolateList());
        return "candy/chocolateList";
    }
    @GetMapping("/{id}")
    public String chocolateInfo(@PathVariable Long id, Model model){
        Chocolate chocolate = chocolateService.findChocolate(id);
        model.addAttribute("product",chocolate );
        return "candy/oneProduct";
    }
    @GetMapping("/add-candy")
    public String createChocolate(Model model){
        model.addAttribute("product", new Chocolate());
        return "candy/productForm";
    }
    @PostMapping("/save")
    public String savingChocolate(@RequestParam("file") MultipartFile file,
                                  Chocolate chocolate) throws Exception{
        chocolateService.saveProductToBD(chocolate, file);
        return "redirect:/candy";
    }
    @GetMapping("/delete/{id}")
    public String deleteChocolate(@PathVariable("id") Long id){
        chocolateService.deleteChocolate(id);
        return "redirect:/candy";
    }
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        Chocolate chocolate = chocolateService.findChocolate(id);
        model.addAttribute("product", chocolate);
        return "candy/productForm";
    }









}
