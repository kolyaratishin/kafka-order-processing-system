package com.orderservice.order.kafka;

import com.orderservice.order.model.OrderMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    private static final String TOPIC = "order.created";
    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(OrderMessage orderMessage) {
        kafkaTemplate.send(TOPIC, orderMessage.getId(), orderMessage);
        System.out.println("Sent order to Kafka: " + orderMessage.getId());
    }
}
