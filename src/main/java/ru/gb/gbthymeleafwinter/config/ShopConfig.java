package ru.gb.gbthymeleafwinter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import ru.gb.gbapi.config.FeignClientFactory;
import ru.gb.gbapi.order.api.OrderGateway;

import java.util.Optional;

@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorAwareBean")
@EnableFeignClients
@RequiredArgsConstructor
@EnableJms
public class ShopConfig {

    private final FeignClientFactory feignClientFactory;

//    @Bean
//    public AuditorAware<String> auditorAwareBean() {
//        return () -> Optional.of("User");
//    }

    @Bean
    public OrderGateway orderGateway() {
        return feignClientFactory.newFeignClient(OrderGateway.class,
                "http://localhost:8080/api/v1/order");
    }

    @Bean
    public MessageConverter messageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

}
