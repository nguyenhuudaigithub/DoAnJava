package com.JS.DA.Admin.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "post")
public class Post {
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

    public boolean getActivate_home() {
        return activate_home;
    }

    public void setActivate_home(boolean activate_home) {
        this.activate_home = activate_home;
    }

    @Column(name = "activate_home")
    private boolean activate_home;
}