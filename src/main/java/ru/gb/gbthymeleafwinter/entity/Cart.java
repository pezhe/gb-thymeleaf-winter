package ru.gb.gbthymeleafwinter.entity;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.gb.gbapi.product.dto.ProductDto;
import ru.gb.gbthymeleafwinter.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Component
public class Cart {

    private final List<ProductDto> products = new ArrayList<>();
    private final ProductService productService;

    public void addProduct(Long id) {
        if (products.stream().anyMatch((p) -> p.getId().equals(id))) return;
        products.add(productService.findById(id));
    }

    public void removeProduct(Long id) {
        products.stream()
                .filter((p) -> p.getId().equals(id))
                .findAny()
                .ifPresent(products::remove);
    }

}
