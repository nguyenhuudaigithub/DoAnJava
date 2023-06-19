package com.JS.DA.Admin.Controller;

import com.JS.DA.Admin.Model.Product;
import com.JS.DA.Admin.Service.ProductImageService;
import com.JS.DA.Admin.Service.ProductService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.ui.Model;
import com.JS.DA.Admin.Model.Category;
import com.JS.DA.Admin.Model.ProductImage;
import com.JS.DA.Admin.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductImageService productImageService;

    // ---------- Sản Phẩm -------------

    // Hiển thị danh sách sản phẩm
    @GetMapping("/admin/products")
    public String productList(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "/admin/product/productList";
    }

    // Thêm sản phẩm
    @GetMapping("/admin/products/add")
    public String addProductForm(Model model) {
        Product product = new Product();
        ProductImage productImage = new ProductImage();
        List<Category> categories = categoryService.getAllCategories();
        List<ProductImage> productImages;
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("productImage",productImage);
        return "/admin/product/addProductForm";
    }

    @PostMapping("/admin/products/add")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("images") List<String> images, @RequestParam("rDefault") List<Integer> rDefault) {
        List<ProductImage> productImages = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            if (i + 1 == rDefault.get(0)) {
                product.setImage(images.get(i));
                productImages.add(new ProductImage(images.get(i), true, product));
            } else {
                productImages.add(new ProductImage(images.get(i), false, product));
            }
        }
        product.setProductImages(productImages);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    // Chỉnh sửa sản phẩm
    @GetMapping("/admin/products/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        List<Category> categories = categoryService.getAllCategories();
        List<ProductImage> productImages = product.getProductImages();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("productImages", productImages);

        return "/admin/product/editProductForm";
    }

    @PostMapping("/admin/products/edit/{id}")
    public String editProduct(@PathVariable("id") Long id,
                              @ModelAttribute("product") Product product,
                              @RequestParam(value = "images", required = false) List<String> images,
                              @RequestParam(value = "rDefault", required = false) List<Integer> rDefault,
                              @RequestParam(value = "imageAdded", required = false) boolean imageAdded) {
        List<ProductImage> productImages = new ArrayList<>();

        if (imageAdded) {
            for (int i = 0; i < images.size(); i++) {
                if (i + 1 == rDefault.get(0)) {
                    product.setImage(images.get(i));
                    productImages.add(new ProductImage(images.get(i), true, product));
                } else {
                    productImages.add(new ProductImage(images.get(i), false, product));
                }
            }
        }else{

        }

        product.setProductImages(productImages);
        productService.updateProduct(product);

        return "redirect:/admin/products";
    }


    //    public String deleteProductImage(@PathVariable("id") Long id) {
//        productImageService.deleteProductImage(id);
//        return "";
//    }
    // Xóa sản phẩm
    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
    //-----------------------------------

    @GetMapping("/search-product-admin")
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

        return "admin/product/productList";
    }
}