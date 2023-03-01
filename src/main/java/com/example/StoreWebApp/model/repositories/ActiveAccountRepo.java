package com.example.StoreWebApp.model.repositories;

import com.example.StoreWebApp.model.entities.ActiveAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveAccountRepo extends JpaRepository<ActiveAccount, Integer> {
}
