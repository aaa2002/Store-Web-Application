package com.example.StoreWebApp.dataTransfer;

import com.example.StoreWebApp.model.entities.AccountType;
import lombok.Data;

@Data
public class UserDataTransfer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Integer typeId;
}
