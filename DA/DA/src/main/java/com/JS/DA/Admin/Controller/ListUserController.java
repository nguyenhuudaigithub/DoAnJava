package com.JS.DA.Admin.Controller;

import com.JS.DA.Login.Model.Role;
import com.JS.DA.Login.Model.User;
import com.JS.DA.Login.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/list-user")
    public String userList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/admin/user/userList";
    }

    @GetMapping("/admin/user/edit/{id}")
    public String editUser(@PathVariable("id") Long userId, Model model) {
        User user = userService.getUserById(userId);
        List<Role> roles = userService.getAllRoles(); // Lấy danh sách tất cả các vai trò
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "/admin/user/editUser";
    }

    @PostMapping("/admin/user/edit/{id}")
    public String updateUser(@PathVariable("id") Long userId, @RequestParam("roleId") Long roleId,
                             @RequestParam("name") String name, @RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam("address") String address) {
        User user = userService.getUserById(userId);
        Role role = userService.getRoleById(roleId);

        // Cập nhật thông tin người dùng
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);

        // Xóa tất cả các vai trò hiện có của người dùng và thêm vai trò mới
        user.getRoles().clear();
        user.getRoles().add(role);

        userService.save(user);
        return "redirect:/admin/list-user";
    }



    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/admin/list-user";
    }
}

