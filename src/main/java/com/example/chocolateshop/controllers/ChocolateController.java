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
    @GetMapping("/list")
    public String allChocolates(Model model){
        model.addAttribute("chocolateList", chocolateService.chocolateList());
        return "candy/chocolateList";
    }
    @GetMapping("/add-candy")
    public String createChocolate(Model model){
        model.addAttribute("newCandy", new Chocolate());
        return "candy/addChocolate";

    }
    @PostMapping
    public String savingChocolate(@RequestParam("file") MultipartFile file,
                                  Chocolate chocolate) throws Exception{
        chocolateService.saveProductToBD(chocolate, file);
        return "redirect:/candy/list";
    }




}
