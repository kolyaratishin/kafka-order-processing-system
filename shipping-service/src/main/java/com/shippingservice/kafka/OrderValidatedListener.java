package com.shippingservice.kafka;

import com.shippingservice.dto.OrderMessage;
import com.shippingservice.service.ShippingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderValidatedListener {

    private final ShippingService shippingService;

    public OrderValidatedListener(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @KafkaListener(topics = "order.validated", groupId = "shipping-group")
    public void listen(OrderMessage orderMessage) {
        System.out.println("📥 Received from order.validated: " + orderMessage.getId());
        shippingService.handleValidatedOrder(orderMessage);
    }
}