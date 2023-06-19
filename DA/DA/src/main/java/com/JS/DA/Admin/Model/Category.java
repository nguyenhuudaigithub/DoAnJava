package com.JS.DA.Admin.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "detail", columnDefinition = "varchar(255)")
    private String detail;

    @Column(name = "image", columnDefinition = "varchar(255)")
    private String image;

    @OneToMany(mappedBy = "category", cascade =  CascadeType.ALL)
    private List<Product> products;
}

