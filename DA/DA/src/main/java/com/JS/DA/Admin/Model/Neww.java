package com.JS.DA.Admin.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "neww")
public class Neww {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "description", columnDefinition = "varchar(255)")
    private String description;

    @Column(name = "detail", columnDefinition = "longblob")
    private String detail;

    public boolean getIs_activate() {
        return is_activate;
    }

    public void setIs_activate(boolean is_activate) {
        this.is_activate = is_activate;
    }

    @Column(name = "is_activate")
    private boolean is_activate;

    @Column(name = "image", columnDefinition = "varchar(255)")
    private String image;

    public boolean isIs_page() {
        return is_page;
    }

    public void setIs_page(boolean is_page) {
        this.is_page = is_page;
    }

    @Column(name = "is_page")
    private boolean is_page;
}