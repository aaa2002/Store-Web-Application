package com.example.StoreWebApp.model.repositories;

import com.example.StoreWebApp.model.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart = new ArrayList<Product>();
    }
}
