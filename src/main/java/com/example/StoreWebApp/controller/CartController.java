package com.example.StoreWebApp.controller;

import com.example.StoreWebApp.dataTransfer.UserDataTransfer;
import com.example.StoreWebApp.model.entities.*;
import com.example.StoreWebApp.model.repositories.ActiveAccountRepo;
import com.example.StoreWebApp.model.repositories.CartRepo;
import com.example.StoreWebApp.model.repositories.GlobalData;
import com.example.StoreWebApp.model.repositories.OrderRepo;
import com.example.StoreWebApp.model.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.*;

import static com.example.StoreWebApp.model.repositories.GlobalData.cart;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ActiveAccountService activeAccountService;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ActiveAccountRepo activeAccountRepo;

    @GetMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable Integer productId) {
        cart.add(productService.getProductById(productId.longValue()).get());

        cartService.addToCart(productId);
        return "redirect:/shop";
    }


    @GetMapping("/cart")
    public String viewCart(Model model) {
        //products = new ArrayList<>();
        /*products.removeAll(products);
        List<Cart> theCart = cartRepo.findAll();
        ListIterator<Cart> cartProductsIterator = theCart.listIterator();
        while (cartProductsIterator.hasNext()) {
            products.add(cartProductsIterator.next().getProduct());
        }*/
        model.addAttribute("cart", cartService.getAllFromCart());

        model.addAttribute("cartSize", cartService.getAllFromCart().size());
        model.addAttribute("totalPrice", cartService.getAllFromCart().stream().mapToDouble(Product::getPrice).sum());
        return "cart";
    }

    @GetMapping("/cart/remove/{productId}")
    public String removeProduct(@PathVariable Integer productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String checkout(Model model) {
        model.addAttribute("order", new Order());
        return "checkout";
    }

    @PostMapping("/cart/checkout")
    public String checkoutPost(@ModelAttribute("order") Order order) throws IOException {

        Order newOrder = new Order();

        if(activeAccountRepo.findAll().size() != 0) {
            User currentUser = activeAccountService.getActiveUser();
            newOrder.setTheUser(currentUser);
        }
        else {
            newOrder.setTheUser(null);
        }

        newOrder.setId(order.getId());
        newOrder.setAddress(order.getAddress());
        newOrder.setCity(order.getCity());
        newOrder.setCountry(order.getCountry());
        newOrder.setCounty(order.getCounty());
        newOrder.setEmail(order.getEmail());
        newOrder.setFirstName(order.getFirstName());
        newOrder.setLastName(order.getLastName());

        newOrder.setDate(java.time.LocalDateTime.now());
        newOrder.setTotal(cartService.getAllFromCart().stream().mapToDouble(Product::getPrice).sum());

        newOrder.setProductList(cartService.getAllFromCart());

        orderRepo.save(newOrder);
        cartRepo.deleteAll();
        return "redirect:/order/placed/" + newOrder.getId();
    }

}
