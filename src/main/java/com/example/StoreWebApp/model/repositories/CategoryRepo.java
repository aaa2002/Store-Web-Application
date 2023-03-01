package com.example.StoreWebApp.model.repositories;

import com.example.StoreWebApp.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
