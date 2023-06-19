package com.JS.DA.Cart.Controller;


import com.JS.DA.Cart.Model.Item;
import com.JS.DA.Cart.Service.CartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping
    public String showCart(HttpSession session,
                           @NotNull Model model) {
        model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("totalPrice",
                cartService.getSumPrice(session));
        model.addAttribute("totalQuantity",
                cartService.getSumQuantity(session));
        return "cart/cart";
    }
    @GetMapping("/removeFromCart/{id}")
    public String removeFromCart(HttpSession session,
                                 @PathVariable Long id) {
        var cart = cartService.getCart(session);
        cart.removeItems(id);
        return "redirect:/cart";
    }
    @GetMapping("/updateCart/{id}/{quantity}")
    public String updateCart(HttpSession session,
                             @PathVariable Long id,
                             @PathVariable int quantity) {
        var cart = cartService.getCart(session);
        cart.updateItems(id.intValue(), quantity);
        return "cart/cart";
    }
    @GetMapping("/clearCart")
    public String clearCart(HttpSession session) {
        cartService.removeCart(session);
        return "redirect:/cart ";
    }
    @GetMapping("/checkout")
    public String checkout(HttpSession session) {
        cartService.saveCart(session);
        return "redirect:/cart";
    }
}
