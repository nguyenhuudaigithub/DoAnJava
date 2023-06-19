package com.JS.DA.Admin.Model;

import com.JS.DA.Cart.Model.ItemInvoice;
import com.JS.DA.Login.Model.User;
import com.JS.DA.Validator.annotation.ValidUserId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "varchar(255)")
    private String description;

    @Column(name = "detail", columnDefinition = "longblob")
    private String detail;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "is_activate")
    private boolean is_activate;

    @Column(name = "price_sale")
    private Long price_sale;

    @Column(name = "image", columnDefinition = "varchar(255)")
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImages;

    public boolean isIs_hot() {
        return is_hot;
    }

    public void setIs_hot(boolean is_hot) {
        this.is_hot = is_hot;
    }

    @Column(name = "is_hot")
    private boolean is_hot;
    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
        for (ProductImage image : productImages) {
            image.setProduct(this);
        }
    }

    public boolean isIs_activate() {
        return is_activate;
    }

    public void setIs_activate(boolean is_activate) {
        this.is_activate = is_activate;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ItemInvoice> itemInvoices = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

