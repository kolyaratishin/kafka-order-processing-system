package com.inventoryservice.kafka;

import com.inventoryservice.dto.OrderMessage;
import com.inventoryservice.service.InventoryService;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private final InventoryService inventoryService;

    public OrderCreatedListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void simulateKafkaMessage(OrderMessage orderMessage) {
        OrderMessage result = inventoryService.checkInventory(orderMessage);

        if (result.getStatus().name().equals("VALIDATED")) {
            System.out.println("Publishing to topic: order.validated → " + result.getId());
        } else {
            System.out.println("Publishing to topic: order.rejected → " + result.getId());
        }

        // Тут буде Kafka-продюсер
    }
}
