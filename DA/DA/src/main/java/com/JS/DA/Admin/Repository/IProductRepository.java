package com.JS.DA.Admin.Repository;

import com.JS.DA.Admin.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long>, JpaRepository<Product, Long> {
    @Query("""
    SELECT p FROM Product p
    WHERE p.name LIKE %?1%
    OR p.description LIKE %?1%
    OR p.category.name LIKE %?1%
    """)
    List<Product> searchProduct(String keyword);
}
