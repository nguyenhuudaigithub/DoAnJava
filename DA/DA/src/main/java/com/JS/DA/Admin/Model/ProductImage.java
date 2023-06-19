package com.JS.DA.Admin.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image", columnDefinition = "varchar(255)")
    private String image;

    @Column(name = "is_default")
    private boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    public ProductImage() {
    }
    public ProductImage(String image, boolean isDefault, Product product) {
        this.image = image;
        this.isDefault = isDefault;
        this.product = product;
    }


}
