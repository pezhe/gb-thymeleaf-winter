package ru.gb.gbthymeleafwinter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.gbapi.order.dto.OrderDto;
import ru.gb.gbthymeleafwinter.service.OrderService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String getOrderList(Model model, @CookieValue(value = "jwt", defaultValue = "") String jwt) {
        model.addAttribute("orders", orderService.getOrders(jwt));
        return "order-list";
    }

    @GetMapping("/new")
    public String showOrderForm(Model model) {
        OrderDto orderDto = new OrderDto();
        model.addAttribute("order", orderDto);
        return "order-form";
    }

    @PostMapping("/new")
    public String processOrderForm(OrderDto orderDto, @CookieValue(value = "jwt", defaultValue = "") String jwt) {
        orderService.addOrder(jwt, orderDto);
        return "redirect:/order";
    }

}
