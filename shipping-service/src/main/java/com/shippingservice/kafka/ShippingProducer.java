package com.shippingservice.kafka;

import com.shippingservice.dto.OrderMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShippingProducer {

    private static final String SHIPPED_TOPIC = "order.shipped";
    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

    public ShippingProducer(KafkaTemplate<String, OrderMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendShipped(OrderMessage order) {
        kafkaTemplate.send(SHIPPED_TOPIC, order.getId(), order);
        System.out.println("🚚 Sent SHIPPED order: " + order.getId());
    }
}
