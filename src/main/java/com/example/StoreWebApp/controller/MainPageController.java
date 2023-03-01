package com.example.StoreWebApp.controller;

import com.example.StoreWebApp.model.entities.User;
import com.example.StoreWebApp.model.services.CategoryService;
import com.example.StoreWebApp.model.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class MainPageController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home", "/index"})
    public String mainPage(Model model){
        return "index";
    }

    @GetMapping("/shop")
    public String shopPage(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopPageCategorySort(Model model, @PathVariable int id){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.listProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/view/{id}")
    public String shopViewProduct(Model model, @PathVariable int id){
        model.addAttribute("product", productService.getProductById(id).get());
        return "viewProduct";
    }
}
