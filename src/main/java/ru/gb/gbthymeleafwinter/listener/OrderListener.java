package ru.gb.gbthymeleafwinter.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.gb.gbapi.events.OrderEvent;
import ru.gb.gbthymeleafwinter.service.MailService;

@RequiredArgsConstructor
@Component
public class OrderListener {

    private final MailService mailService;

    @JmsListener(destination = "order-changed")
    public void listen(@Payload OrderEvent orderEvent) {
        System.out.println(orderEvent.getOrderDto());
        mailService.sendSimpleMessage("pezhe@list.ru", "Order change information",
                orderEvent.getOrderDto().toString());
        System.out.println("After mail sending");
    }
}