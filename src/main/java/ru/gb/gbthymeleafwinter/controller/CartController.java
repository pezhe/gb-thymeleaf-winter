package ru.gb.gbthymeleafwinter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbthymeleafwinter.entity.Cart;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final Cart cart;

    @GetMapping
    @PreAuthorize("hasAuthority('cart.add')")
    public String getProductList(Model model) {
        model.addAttribute("products", cart.getProducts());
        return "cart-list";
    }

    @GetMapping("/remove")
    @PreAuthorize("hasAuthority('cart.add')")
    public String removeProduct(@RequestParam(name = "id") Long id) {
        cart.removeProduct(id);
        return "redirect:/cart/";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('cart.add')")
    public String addProduct(@RequestParam(name = "id") Long id) {
        cart.addProduct(id);
        return "redirect:/product/all";
    }

    @GetMapping("/unauthorized")
    public String redirectToLoginPage() {
        return "cart-unauthorized";
    }

}
