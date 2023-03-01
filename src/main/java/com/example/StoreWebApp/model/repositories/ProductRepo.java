package com.example.StoreWebApp.model.repositories;

import com.example.StoreWebApp.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryCategoryId(int categoryId);
}
