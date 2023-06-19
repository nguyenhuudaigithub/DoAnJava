package com.JS.DA.Admin.Service;


import com.JS.DA.Admin.Model.Product;
import com.JS.DA.Admin.Repository.IProductRepository;
import com.JS.DA.Admin.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private IProductRepository iproductRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public List<Product> getAllProductWithCategories() {
        return productRepository.findAllWithCategories();
    }

    public Product getProductById(Long product) {
        Optional<Product> optional = productRepository.findById(product);
        return optional.orElse(null);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct (Product product) {
        productRepository.save(product);
    }

    public void deleteProduct (Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> searchProduct(String keyword) {
        return iproductRepository.searchProduct(keyword);
    }
}
