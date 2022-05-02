package ru.gb.gbthymeleafwinter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.gbapi.product.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final List<ProductDto> products = new ArrayList<>();
    private final ProductService productService;

    public List<ProductDto> getProducts() {
        return products;
    }

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

    public void clear() {
        products.clear();
    }

}
