package com.example.StoreWebApp.model.services;

import com.example.StoreWebApp.model.entities.Cart;
import com.example.StoreWebApp.model.entities.Product;
import com.example.StoreWebApp.model.repositories.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProductService productService;

    public List<Product> getAllFromCart() {
        List<Product> products = new ArrayList<>();
        List<Cart> tempCart = cartRepo.findAll();

        for(Cart iterator : tempCart) {
            products.add(iterator.getProduct());
        }
        return products;
    }

    public void addToCart(Integer productId) {
        Cart myCart = new Cart();
        myCart.setProduct(productService.getProductById(productId.longValue()).get());
        cartRepo.save(myCart);
    }

    public void removeFromCart(Integer productId) {
        List<Cart> theCart = cartRepo.findAll();

        for (Cart iterator : theCart) {
            System.out.println("Product ID: " + iterator.getProduct().getProductId());
            System.out.println("Searched ID: " + productId);

            if (productId.equals(iterator.getProduct().getProductId())) {
                cartRepo.deleteById(iterator.getId().intValue());
                System.out.println("DELETED!!!!!!!");
                return;
            }
        }
    }
}
