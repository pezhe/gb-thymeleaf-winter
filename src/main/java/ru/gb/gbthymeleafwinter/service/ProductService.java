package ru.gb.gbthymeleafwinter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.gb.gbapi.product.api.ProductGateway;
import ru.gb.gbapi.product.dto.ProductDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductGateway productGateway;

    public void save(ProductDto productDto) {
        if (productGateway.getProduct(productDto.getId()).getStatusCode().equals(HttpStatus.OK)) {
            productGateway.handleUpdate(productDto.getId(), productDto);
        } else {
            productGateway.handlePost(productDto);
        }
    }

    public ProductDto findById(Long id) {
        return productGateway.getProduct(id).getBody();
    }

    public List<ProductDto> findAll() {
        return productGateway.getProductList();
    }

    public void deleteById(Long id) {
        productGateway.deleteById(id);
    }

}
