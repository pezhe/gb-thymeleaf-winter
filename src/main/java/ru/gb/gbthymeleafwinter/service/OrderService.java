package ru.gb.gbthymeleafwinter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.gbapi.common.enums.OrderStatus;
import ru.gb.gbapi.order.api.OrderGateway;
import ru.gb.gbapi.order.dto.OrderDto;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final CartService cart;
    private final OrderGateway orderGateway;

    public List<OrderDto> getOrders() {
        return orderGateway.getOrderList();
    }

    public void addOrder(OrderDto order) {
        order.setStatus(OrderStatus.CREATED);
        order.setProducts(new HashSet<>(cart.getProducts()));
        orderGateway.handlePost(order);
        cart.clear();
    }

}
