package com.JS.DA.Admin.Controller;

import org.springframework.ui.Model;
import com.JS.DA.Admin.Model.Category;
import com.JS.DA.Admin.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoyController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/categories")
    public String categoryList(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/admin/category/categoryList";
    }

    @GetMapping("/admin/categories/add")
    public String addCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "/admin/category/addCategoryForm";
    }

    // Mapping for adding a new category
    @PostMapping("/admin/categories/add")
    public String addCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    // Mapping for displaying edit category form
    @GetMapping("/admin/categories/edit/{id}")
    public String editCategoryForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "/admin/category/editCategoryForm";
    }

    // Mapping for updating a category
    @PostMapping("/admin/categories/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, @ModelAttribute("category") Category updatedCategory) {
        Category existingCategory = categoryService.getCategoryById(id);
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setImage(updatedCategory.getImage());
        existingCategory.setDetail(updatedCategory.getDetail());
        categoryService.updateCategory(existingCategory);
        return "redirect:/admin/categories";
    }


    // Mapping for deleting a category
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
}
