package com.shippingservice.kafka;

import com.shippingservice.dto.OrderMessage;
import com.shippingservice.service.ShippingService;
import org.springframework.stereotype.Component;

@Component
public class OrderValidatedListener {

    private final ShippingService shippingService;

    public OrderValidatedListener(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void simulateKafkaMessage(OrderMessage orderMessage) {
        if (orderMessage.getStatus() == null || !orderMessage.getStatus().equals("VALIDATED")) {
            System.out.println("Skipping non-validated order");
            return;
        }

        OrderMessage shipped = shippingService.shipOrder(orderMessage);
        System.out.println("Publishing to topic: order.shipped → " + shipped.getId());

        // Тут буде Kafka-продюсер
    }
}