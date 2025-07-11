package com.orderservice.order.kafka;

import com.orderservice.order.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    private static final String TOPIC = "order.created";
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Order order) {
        kafkaTemplate.send(TOPIC, order.getId(), order);
        System.out.println("Sent order to Kafka: " + order.getId());
    }
}
