package ru.gb.gbthymeleafwinter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbthymeleafwinter.service.CartService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String getProductList(Model model) {
        model.addAttribute("products", cartService.getProducts());
        return "cart-list";
    }

    @GetMapping("/remove")
    public String removeProduct(@RequestParam(name = "id") Long id) {
        cartService.removeProduct(id);
        return "redirect:/cart/";
    }

    @GetMapping("/add")
    public String addProduct(@RequestParam(name = "id") Long id) {
        cartService.addProduct(id);
        return "redirect:/product/all";
    }

}
