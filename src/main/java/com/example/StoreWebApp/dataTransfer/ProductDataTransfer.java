package com.example.StoreWebApp.dataTransfer;

import com.example.StoreWebApp.model.entities.Category;
import lombok.Data;

@Data
public class ProductDataTransfer {
    private Integer productId;
    private String productName;
    private Integer categoryId;
    private Double price;
    private String description;
    private String imgFileName;
}
