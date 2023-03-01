package com.example.StoreWebApp.controller;

import com.example.StoreWebApp.dataTransfer.UserDataTransfer;
import com.example.StoreWebApp.model.entities.*;
import com.example.StoreWebApp.model.repositories.AccountTypeRepo;
import com.example.StoreWebApp.model.repositories.ActiveAccountRepo;
import com.example.StoreWebApp.model.repositories.CartRepo;
import com.example.StoreWebApp.model.repositories.ProductRepo;
import com.example.StoreWebApp.model.services.ProductService;
import com.example.StoreWebApp.model.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService service;

    @Autowired
    private ProductService productService;


    @Autowired
    private ActiveAccountRepo activeAccountRepo;
    @Autowired
    private AccountTypeRepo accountTypeRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;

    /*@RequestMapping("/create")
    public void addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("userDataTransfer") UserDataTransfer userDataTransfer) throws IOException {
        String firstName = request.getParameter("firstName").toString();
        String lastName = request.getParameter("lastName").toString();
        String email = request.getParameter("email").toString();
        String username = request.getParameter("username").toString();
        String password = request.getParameter("password").toString();
        String confirmPassword = request.getParameter("confirmPassword").toString();

        User user = new User();
        user.setFirstName(firstName);
        userDataTransfer.setFirstName(firstName);
        user.setLastName(lastName);
        userDataTransfer.setLastName(lastName);
        user.setUsername(username);
        userDataTransfer.setUsername(username);
        user.setType(service.getTypeById(userDataTransfer.getTypeId()));

        String regexPattern = "^(.+)@(\\S+)$";
        if (Pattern.compile(regexPattern).matcher(email).matches() && password.equals(confirmPassword)) //checks if it's an email
        {
            user.setEmail(email);
            userDataTransfer.setEmail(email);
            user.setPassword(password);
            userDataTransfer.setPassword(password);
            service.save(user);
            response.sendRedirect("/index");
        } else {
            response.sendRedirect("/user/register");
        }
    }
*/
    @GetMapping("/register")
    public String addProduct(Model model) {
        model.addAttribute("userDataTransfer", new UserDataTransfer());
        model.addAttribute("accountTypes", accountTypeRepo.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String addProductAndPost(@ModelAttribute("userDataTransfer") UserDataTransfer userDataTransfer) throws IOException {
        User user = new User();
        user.setId(userDataTransfer.getId());
        user.setEmail(userDataTransfer.getEmail());
        user.setType(accountTypeRepo.findAccountTypeById(userDataTransfer.getTypeId()));
        user.setUsername(userDataTransfer.getUsername());
        user.setPassword(userDataTransfer.getPassword());
        user.setFirstName(userDataTransfer.getFirstName());
        user.setLastName(userDataTransfer.getLastName());

        service.save(user);

        return "redirect:/user/login";
    }

    /*@RequestMapping("/register")
    public String register(HttpServletRequest request) {
        return "register.html";
    }*/

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        return "login.html";
    }

    @PostMapping("/login")
    public String loginCheck(@ModelAttribute(name = "login") Login login, Model model) {

        User user = new User();
        ActiveAccount activeAccount = new ActiveAccount();

        //checks for credentials
        String username = login.getUsername();
        String password = login.getPassword();

        user = service.login(username, password);

        if ("admin".equals(username) && "admin".equals(password)) {
            //admin redirect
            return "redirect:/admin";
        }

        if (user != null) {
            //user redirect
            activeAccount.setUser(user);
            activeAccountRepo.save(activeAccount);
            return "redirect:/shop";
        }

        //for wrong credentials
        model.addAttribute("invalidCredentials", true);
        System.out.println("Login attempt failed for username '" + username + "' and password '" + password + "'. User not found.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        activeAccountRepo.deleteAll();
        cartRepo.deleteAll();
        return "redirect:/shop";
    }

    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
        List<User> users = service.getAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list-users.html");
        mv.addObject("myUsers", users);
        return mv;
    }
}
