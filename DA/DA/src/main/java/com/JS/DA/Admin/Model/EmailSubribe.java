package com.JS.DA.Admin.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "EmailSubribe")
public class EmailSubribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", columnDefinition = "varchar(255)")
    private String email;
}
