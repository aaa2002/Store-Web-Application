package com.example.StoreWebApp.controller;

import com.example.StoreWebApp.dataTransfer.ProductDataTransfer;
import com.example.StoreWebApp.model.entities.AccountType;
import com.example.StoreWebApp.model.entities.Category;
import com.example.StoreWebApp.model.entities.Product;
import com.example.StoreWebApp.model.entities.User;
import com.example.StoreWebApp.model.repositories.AccountTypeRepo;
import com.example.StoreWebApp.model.repositories.ActiveAccountRepo;
import com.example.StoreWebApp.model.repositories.CategoryRepo;
import com.example.StoreWebApp.model.services.CategoryService;
import com.example.StoreWebApp.model.services.ProductService;
import com.example.StoreWebApp.model.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Controller
public class AdminController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    ProductService productService;
    @Autowired
    private AccountTypeRepo accountTypeRepo;
    @Autowired
    private ActiveAccountRepo activeAccountRepo;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    //========Category========

    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category()); //same as in th:object = "${category}"
        return "addCategory";
    }

    @PostMapping("/admin/categories/add")
    public String postCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/remove/{id}")
    public String removeCategory(@PathVariable int id) {
        categoryService.removeCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "updateCategory";
        } else {
            return "404";
        }
    }


    //======== Admin - Product List - (Test) ========
    @GetMapping("/admin/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products-admin";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model) {
        model.addAttribute("productDataTransfer", new ProductDataTransfer());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "products-add";
    }

    @PostMapping("/admin/products/add")
    public String addProductAndPost(@ModelAttribute("productDataTransfer") ProductDataTransfer productDataTransfer,
                                    @RequestParam("imgFile")MultipartFile file,
                                    @RequestParam("imgName")String fileName) throws IOException {
        Product product = new Product();
        product.setProductId(productDataTransfer.getProductId());
        product.setProductName(productDataTransfer.getProductName());
        product.setCategory(categoryService.getCategoryById(productDataTransfer.getCategoryId()).get());
        product.setPrice(productDataTransfer.getPrice());
        product.setDescription(productDataTransfer.getDescription());
        String imgID;
        if (!file.isEmpty()) {
            imgID = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, imgID);
            Files.write(filePath, file.getBytes());
        }
        else {
            imgID = fileName;
        }

        product.setImgFileName(imgID);

        productService.addProduct(product);

        return "redirect:/shop";
    }

    @GetMapping("/admin/products/remove/{id}")
    public String removeProduct(@PathVariable long id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/update/{id}")
    public String updateProduct(@PathVariable long id, Model model) {
        Product product = productService.getProductById(id).get();
        ProductDataTransfer productDataTransfer = new ProductDataTransfer();

        productDataTransfer.setProductId(product.getProductId());
        productDataTransfer.setProductName(product.getProductName());
        productDataTransfer.setCategoryId(product.getCategory().getCategoryId());
        productDataTransfer.setPrice(product.getPrice());
        productDataTransfer.setDescription(product.getDescription());
        productDataTransfer.setImgFileName(product.getImgFileName());

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("productDataTransfer", productDataTransfer);

        return "products-update";
    }

    //=================Account Types=================

    @GetMapping("/admin/accountTypes")
    public String getAccountTypes(Model model) {
        model.addAttribute("accountTypes", accountTypeRepo.findAll());
        return "accountTypes";
    }

    @GetMapping("/admin/accountTypes/add")
    public String addAccountType(Model model) {
        model.addAttribute("accountType", new AccountType());
        return "accTypeAdd";
    }

    @PostMapping("/admin/accountTypes/add")
    public String postAccountType(@ModelAttribute("accountType") AccountType accountType) {
        accountTypeRepo.save(accountType);
        return "redirect:/admin/accountTypes";
    }

    @GetMapping("/admin/accountTypes/remove/{id}")
    public String removeAccountType(@PathVariable int id) {
        accountTypeRepo.delete(accountTypeRepo.findAccountTypeById(id));
        return "redirect:/admin/accountTypes";
    }

    @GetMapping("/admin/accountTypes/update/{id}")
    public String updateAccountType(@PathVariable int id, Model model) {

        AccountType accountType = accountTypeRepo.getReferenceById(id);
        /*if (accountType.isPresent()) {
            model.addAttribute("accountType", accountType.get());
            return "accTypeAdd";
        }
        else return "404";*/
        model.addAttribute("accountType", accountType);
        return "accTypeAdd";
    }
}
