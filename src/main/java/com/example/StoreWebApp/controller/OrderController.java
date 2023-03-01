package com.example.StoreWebApp.controller;

import com.example.StoreWebApp.model.entities.Order;
import com.example.StoreWebApp.model.entities.User;
import com.example.StoreWebApp.model.repositories.OrderRepo;
import com.example.StoreWebApp.model.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/orders/list")
    public ModelAndView list(HttpServletRequest request) {
        List<Order> orders = orderService.getAllOrders();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list-orders.html");
        mv.addObject("myOrders", orders);
        return mv;
    }

    @GetMapping("/order/placed/{orderId}")
    public String viewOrder(Model model, @PathVariable Integer orderId) {
        Order order = orderService.findById(orderId.intValue());
        model.addAttribute("order", order);
        return "viewOrder";
    }
}
