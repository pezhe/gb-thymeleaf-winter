package ru.gb.gbthymeleafwinter.clients;

import feign.Headers;
import feign.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.gb.gbapi.order.dto.OrderDto;

import java.util.List;

public interface OrderExternalGateway {

    @GetMapping
    List<OrderDto> getOrderList(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader);

    @PostMapping
    ResponseEntity<OrderDto> handlePost(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                        @Validated @RequestBody OrderDto orderDto);

}
