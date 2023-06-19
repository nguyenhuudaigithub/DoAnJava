package com.JS.DA.Admin.Service;


import com.JS.DA.Admin.Model.ProductImage;
import com.JS.DA.Admin.Repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository productImageService;

    public List<ProductImage> getAllProductImage(){
        return productImageService.findAll();
    }

    public ProductImage getProductImageById(Long id) {
        Optional<ProductImage> optional = productImageService.findById(id);
        return optional.orElse(null);
    }

    public void addProductImage(ProductImage productImage) {
        productImageService.save(productImage);
    }

    public void updateProductImage (ProductImage productImage) {
        productImageService.save(productImage);
    }

    public void deleteProductImage (Long id) {
        productImageService.deleteById(id);
    }
}

