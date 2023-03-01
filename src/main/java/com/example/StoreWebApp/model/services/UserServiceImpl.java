package com.example.StoreWebApp.model.services;

import com.example.StoreWebApp.model.entities.AccountType;
import com.example.StoreWebApp.model.entities.User;
import com.example.StoreWebApp.model.repositories.AccountTypeRepo;
import com.example.StoreWebApp.model.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private AccountTypeRepo accountTypeRepo;

    @Override
    public void save(User user) {
        repo.save(user);
    }

    public User findById(Integer id) {
        return repo.findById(id.longValue()).get();
    }

    @Override
    public void remove(User user) {
        repo.delete(user);
    }
    @Override
    public User login(String username, String password) {
        return repo.findByUsernameAndPassword(username, password).orElse(null);
    }

    @Override
    public AccountType getTypeById(int id) {
        return accountTypeRepo.findAccountTypeById(id);
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }
}
