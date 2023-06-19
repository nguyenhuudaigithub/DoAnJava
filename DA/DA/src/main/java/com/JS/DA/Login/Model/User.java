package com.JS.DA.Login.Model;

import com.JS.DA.Admin.Model.Product;
import com.JS.DA.Validator.annotation.ValidEmail;
import com.JS.DA.Validator.annotation.ValidUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotBlank(message = "Vui lòng nhập tài khoản!")
    @Size(max = 50, message = "Username must be less than 50 characters")
    @ValidUsername
    private String username;
    @Column(name = "password", length = 250, nullable = false)
    @NotBlank(message = "Vui lòng nhập mật khẩu!")
    private String password;
    @Column(name = "email", length = 50)
    @Size(max = 50, message = "Email must be less than 50 characters")
    @ValidEmail
    @NotBlank(message = "Vui lòng nhập Email!")
    private String email;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "address")
    @NotBlank(message = "Vui lòng nhập địa chỉ!")
    private String address;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "phoneNumber")
    @NotBlank(message = "Vui lòng nhập số điện thoại!")
    private String phoneNumber;
    @Column(name = "name", length = 50, nullable = false)
    @Size(max = 50, message = "Your name must be less than 50 characters")
    @NotBlank(message = "Vui lòng nhập tên người dùng")
    private String name;
    @ManyToMany
    @JoinTable(name ="user_role",
            joinColumns =@JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Column(name = "provider", length = 50)
    private String provider;

}
