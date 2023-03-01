package com.example.StoreWebApp.model.repositories;

import com.example.StoreWebApp.model.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTypeRepo extends JpaRepository<AccountType, Integer> {
    public AccountType findAccountTypeById(int id);
}
