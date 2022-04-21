package ru.gb.gbthymeleafwinter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.gbapi.common.enums.OrderStatus;
import ru.gb.gbapi.order.dto.OrderDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final List<OrderDto> orders = new ArrayList<>();
    private final CartService cart;
    private Long pseudoID = 0L;

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void addOrder(OrderDto order) {
        order.setId(++pseudoID);
        order.setStatus(OrderStatus.CREATED);
        order.setProducts(new HashSet<>(cart.getProducts()));
        log.info(String.valueOf(order.getId()));
        orders.add(order);
    }

}
