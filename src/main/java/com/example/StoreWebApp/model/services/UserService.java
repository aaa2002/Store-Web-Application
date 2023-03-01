package com.example.StoreWebApp.model.services;

import com.example.StoreWebApp.model.entities.AccountType;
import com.example.StoreWebApp.model.entities.User;

import java.util.List;

public interface UserService {
    public void save(User user);
    public void remove(User user);
    public User login(String username, String password);
    public AccountType getTypeById(int id);

    public List<User> getAll();
}
