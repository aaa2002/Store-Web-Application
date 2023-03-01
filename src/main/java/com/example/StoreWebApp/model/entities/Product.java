package com.example.StoreWebApp.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Reference;

import java.util.List;

@Entity
@Data
@Table(name = "Product")
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer productId;
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category category;

    private Double price;
    private String description;
    private String imgFileName;

    @ManyToMany(mappedBy = "productList")
    private List<Order> orderList;
}
