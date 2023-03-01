package com.example.StoreWebApp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    //@ManyToOne
    //@JoinColumn(name = "user", nullable=false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private User theUser;
    private String firstName;
    private String lastName;
    private String email;

    private String country;
    private String county;
    private String city;
    private String address;

    private Double total;
    private LocalDateTime date;

    @ManyToMany
    @JoinTable(
            name = "Orders_Product",
            joinColumns = { @JoinColumn(name = "orders_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_productId") }
    )
    private List<Product> productList;
}
