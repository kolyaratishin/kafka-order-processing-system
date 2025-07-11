package com.inventoryservice.kafka;

import com.inventoryservice.dto.OrderMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryProducer {

    private static final String VALIDATED_TOPIC = "order.validated";
    private static final String REJECTED_TOPIC = "order.rejected";

    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

    public InventoryProducer(KafkaTemplate<String, OrderMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendValidated(OrderMessage order) {
        kafkaTemplate.send(VALIDATED_TOPIC, order.getId(), order);
        System.out.println("✅ Sent VALIDATED order: " + order.getId());
    }

    public void sendRejected(OrderMessage order) {
        kafkaTemplate.send(REJECTED_TOPIC, order.getId(), order);
        System.out.println("❌ Sent REJECTED order: " + order.getId());
    }
}
