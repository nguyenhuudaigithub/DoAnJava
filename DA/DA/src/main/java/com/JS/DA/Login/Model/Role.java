package com.JS.DA.Login.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;
import java.util.HashSet;

@Data
@Entity
@Table(name ="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "Name must be less than 50 characters")
    @NotBlank(message = "Name is requires")
    @Column(name ="name", length =50 , nullable = false)
    private String name;

    @Size(max = 250,message = "Description must be less than 250 characters")
    @Column(name ="description", length = 250)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> uset = new HashSet<>();

}
