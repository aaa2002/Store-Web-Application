package com.example.StoreWebApp.model.services;

import com.example.StoreWebApp.model.entities.ActiveAccount;
import com.example.StoreWebApp.model.entities.User;
import com.example.StoreWebApp.model.repositories.ActiveAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveAccountService {
    @Autowired
    ActiveAccountRepo activeAccountRepo;

    public User getActiveUser() {
        List<ActiveAccount> activeUserss = activeAccountRepo.findAll();
        if (activeUserss.size() != 0) {
            return activeUserss.get(0).getUser();
        }
        else return null;
    }
}
