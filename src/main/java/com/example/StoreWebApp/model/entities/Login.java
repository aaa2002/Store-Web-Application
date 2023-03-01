package com.example.StoreWebApp.model.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Login {
    private String username;
    private String password;

    public  Login() {
        super();
    }
}
