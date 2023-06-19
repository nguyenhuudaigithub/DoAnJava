package com.JS.DA.Cart.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    private Long productId;


    public String getProductName() {
        return productName;
    }

    private String productName;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    private int quantity;
}
