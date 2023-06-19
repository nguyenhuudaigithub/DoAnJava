package com.JS.DA.User.Controller;

import com.JS.DA.Admin.Model.Category;
import com.JS.DA.Admin.Model.Neww;
import com.JS.DA.Admin.Model.Product;
import com.JS.DA.Admin.Model.ProductImage;
import com.JS.DA.Admin.Service.CategoryService;
import com.JS.DA.Admin.Service.ProductService;
import com.JS.DA.Cart.Model.Item;
import com.JS.DA.Cart.Service.CartService;
import jakarta.servlet.http.HttpSession;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MenuController {
    @Autowired
    private ProductService productService;
    @Autowired
    private  CategoryService categoryService;
    @Autowired
    private CartService cartService;
    @GetMapping("/menu")
    public String showMenu(@RequestParam(name = "categoryId", required = false) Long categoryId, Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        if (categoryId != null) {
            Category selectedCategory = categoryService.getCategoryById(categoryId);
            model.addAttribute("selectedCategory", selectedCategory);
            model.addAttribute("products", selectedCategory.getProducts());
        } else {
            List<Product> allProducts = productService.getAllProduct();
            model.addAttribute("products", allProducts);
        }
        return "/user/menu/index";
    }

    @GetMapping("/menu/{id}")
    public String readProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        List<Category> categories = categoryService.getAllCategories();
        List<ProductImage> productImages = product.getProductImages();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("productImages", productImages);

        return "/user/menu/readProductForm";
    }
    @GetMapping("/search")
    public String searchProduct(
            @NotNull Model model,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<Product> products = productService.searchProduct(keyword);
        int totalProducts = productService.getAllProduct().size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "user/menu/index";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(HttpSession session,
                            @RequestParam long id,
                            @RequestParam String name,
                            @RequestParam double price,
                            @RequestParam(defaultValue = "1") int quantity)
    {
        var cart = cartService.getCart(session);
        cart.addItems(new Item(id, name, price, quantity));
        cartService.updateCart(session, cart);
        return "redirect:/menu";
    }
}
