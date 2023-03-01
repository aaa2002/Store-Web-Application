package com.example.StoreWebApp.model.services;

import com.example.StoreWebApp.model.entities.Category;
import com.example.StoreWebApp.model.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;


    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public void addCategory(Category category) {
        categoryRepo.save(category);
    }

    public void removeCategory(int id) {
        categoryRepo.deleteById(id);
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryRepo.findById(id);
    }
}
