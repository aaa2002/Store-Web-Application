package com.example.StoreWebApp.model.entities;

import com.example.StoreWebApp.model.repositories.ActiveAccountRepo;
import com.example.StoreWebApp.model.services.ActiveAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @Autowired
    ActiveAccountService activeAccountService;
    @Autowired
    private ActiveAccountRepo activeAccountRepo;

    @ModelAttribute("activeUser")
    public boolean getIfActiveUser() {
        return activeAccountService.getActiveUser() != null;
    }

    @ModelAttribute("isSeller")
    public boolean isSeller() {
        if (activeAccountService.getActiveUser() != null) {
            return activeAccountService.getActiveUser().getType().getId() == 252;
        }
        else return false;
    }
}
