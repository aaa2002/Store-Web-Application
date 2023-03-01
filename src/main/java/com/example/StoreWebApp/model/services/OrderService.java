package com.example.StoreWebApp.model.services;

import com.example.StoreWebApp.model.entities.Order;
import com.example.StoreWebApp.model.entities.Product;
import com.example.StoreWebApp.model.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;


    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public void sendOrder(Order order) {
        orderRepo.save(order);
    }

    public Order findById(Integer id) {
        return orderRepo.findById(id).get();
    }
}
