package com.JS.DA.Login.Service;


import com.JS.DA.Login.Model.Role;
import com.JS.DA.Login.Model.User;
import com.JS.DA.Login.Repository.IUserRepository;
import com.JS.DA.Login.Repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    public List<User> getAllUsers() {
        // Gọi phương thức từ userRepository để lấy danh sách tất cả người dùng
        return userRepository.findAll();
    }
    public void save(User user) {
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if(roleId != 0 && userId != 0){
            userRepository.addRoleToUser(userId,roleId);
        }
    }
    public User findByUsername(String username) {
        // Gọi phương thức từ userRepository để tìm người dùng theo tên tài khoản
        return userRepository.findByUsername(username);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<Role> getRolesByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return new ArrayList<>(user.getRoles());
        }
        return Collections.emptyList();
    }

    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}