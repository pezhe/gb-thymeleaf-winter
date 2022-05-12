package ru.gb.gbthymeleafwinter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import ru.gb.gbapi.config.FeignClientFactory;
import ru.gb.gbapi.product.api.ProductGateway;
import ru.gb.gbthymeleafwinter.clients.AuthGateway;
import ru.gb.gbthymeleafwinter.clients.OrderExternalGateway;
import ru.gb.gbthymeleafwinter.clients.UserGateway;

@Configuration
@EnableFeignClients
@RequiredArgsConstructor
@EnableJms
public class ShopConfig {

    private final FeignClientFactory feignClientFactory;

    @Bean
    public OrderExternalGateway orderExternalGateway() {
        return feignClientFactory.newFeignClient(OrderExternalGateway.class,
                "http://localhost:8081/external/api/v1/order");
    }

    @Bean
    @Primary
    public ProductGateway productExternalGateway() {
        return feignClientFactory.newFeignClient(ProductGateway.class,
                "http://localhost:8081/external/api/v1/product");
    }

    @Bean
    public UserGateway userGateway() {
        return feignClientFactory.newFeignClient(UserGateway.class,
                "http://localhost:8081/external/api/v1/user");
    }

    @Bean
    public AuthGateway authGateway() {
        return feignClientFactory.newFeignClient(AuthGateway.class,
                "http://localhost:8081/external/api/v1/auth/login");
    }

    @Bean
    public MessageConverter messageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

}
