package com.example.StoreWebApp.model.repositories;

import com.example.StoreWebApp.model.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {
}
