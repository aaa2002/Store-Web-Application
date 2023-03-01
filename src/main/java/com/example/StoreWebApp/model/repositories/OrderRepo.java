package com.example.StoreWebApp.model.repositories;

import com.example.StoreWebApp.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
