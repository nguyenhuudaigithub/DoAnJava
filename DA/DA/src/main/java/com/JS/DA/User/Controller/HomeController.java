package com.JS.DA.User.Controller;

import com.JS.DA.Admin.Model.Category;
import com.JS.DA.Admin.Model.Neww;
import com.JS.DA.Admin.Model.Post;
import com.JS.DA.Admin.Model.Product;
import com.JS.DA.Admin.Service.CategoryService;
import com.JS.DA.Admin.Service.NewwService;
import com.JS.DA.Admin.Service.PostService;
import com.JS.DA.Admin.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Component("userHomeController")
@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    @Autowired
    private ProductService productService;
    @Autowired
    private NewwService newwService;
    @GetMapping("/")
    public String categoryTop(Model model) {
//        Danh Mục Sản Phẩm
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

//        Bài Viết
        List<Post> posts = postService.getAllPost();
        List<Post> filteredPosts = new ArrayList<>();

        for (Post post : posts) {
            if (post.getIs_activate() && post.getActivate_home()) {
                filteredPosts.add(post);
            }
        }
        model.addAttribute("posts", filteredPosts);

//        Sản Phảm Nổi Bật
        List<Product> products = productService.getAllProductWithCategories();
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.isIs_activate() && product.isIs_hot()) {
                filteredProducts.add(product);
            }
        }
        model.addAttribute("products", filteredProducts);

//      Tin Tức
        List<Neww> newws = newwService.getAllNeww();
        List<Neww> filteredNewws = new ArrayList<>();
        for (Neww neww : newws) {
            if (neww.getIs_activate() && neww.isIs_page()) {
                filteredNewws.add(neww);
            }
        }
        model.addAttribute("newws", filteredNewws);

        return "/user/home/index";
    }
}
