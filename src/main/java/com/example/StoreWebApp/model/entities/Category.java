package com.example.StoreWebApp.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;
import static jakarta.persistence.GenerationType.TABLE;

@Entity
@Table(name = "Category")
@Setter
@Getter
public class Category {
    @Id
    //@TableGenerator(name = "category", table = "myCategories")
    @GeneratedValue(strategy=SEQUENCE)

   // @GeneratedValue(strategy = TABLE, generator = "category")
    @Column(name = "categoryId")
    private Integer categoryId;
    private String categoryName;
    //product categories
}
