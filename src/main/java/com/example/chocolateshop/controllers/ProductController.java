package com.example.chocolateshop.controllers;


import com.example.chocolateshop.models.Product;
import com.example.chocolateshop.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String allProducts(Model model){
        return findPaginated(1, model);
    }
    @GetMapping("/{id}")
    public String chocolateInfo(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findProduct(id));
        return "candy/oneProduct";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model){
        int pageSize = 50;
        Page<Product> page = productService.findPaginated(pageNo, pageSize);
        List<Product> productList = page.getContent();
        List<Product> newProductList = new ArrayList<>();
        for(Product product : productList){
            if(product.getEnabled() == 1){
                newProductList.add(product);
            }else {
                continue;
            }
        }
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("productList", newProductList);
        return "candy/chocolateList";
    }
    @GetMapping("/deleted")
    public String deletedProducts(Model model){
        List<Product> allProducts = productService.getAll();
        List<Product> deletedProduct  = new ArrayList<>();
        for(Product product : allProducts){
            if(product.getEnabled() == 0){
                deletedProduct.add(product);
            }
        }
        model.addAttribute("deletedProducts", deletedProduct);
        return "deleted";
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

    @GetMapping("/bucket/{id}")
    public String addBucket(@PathVariable Long id, Principal principal){
        if(principal == null){
            return "redirect:/products";
        }
        productService.addToUserBucket(id, principal.getName());
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
