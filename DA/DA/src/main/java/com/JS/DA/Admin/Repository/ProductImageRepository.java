package com.JS.DA.Admin.Repository;

import com.JS.DA.Admin.Model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long>{
}

