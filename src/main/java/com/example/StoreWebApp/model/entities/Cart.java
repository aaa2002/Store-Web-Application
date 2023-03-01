package com.example.StoreWebApp.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "cart")
@Setter
@Getter
@NoArgsConstructor
public class Cart {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = true)
    private Long id;

    public Long getId() {
        return this.id;
    }

    @ManyToOne
    @JoinColumn(name = "products_product_id")
    Product product;
}
