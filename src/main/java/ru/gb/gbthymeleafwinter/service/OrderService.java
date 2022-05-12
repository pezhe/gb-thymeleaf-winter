package ru.gb.gbthymeleafwinter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.gbapi.common.enums.OrderStatus;
import ru.gb.gbapi.order.dto.OrderDto;
import ru.gb.gbthymeleafwinter.clients.OrderExternalGateway;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final CartService cart;
    private final OrderExternalGateway orderGateway;

    public List<OrderDto> getOrders(String jwt) {
        return orderGateway.getOrderList("Bearer " + jwt);
    }

    public void addOrder(String jwt, OrderDto order) {
        order.setStatus(OrderStatus.CREATED);
        order.setProducts(new HashSet<>(cart.getProducts()));
        orderGateway.handlePost("Bearer " + jwt, order);
        cart.clear();
    }

}
