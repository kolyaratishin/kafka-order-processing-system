package com.inventoryservice.kafka;

import com.inventoryservice.dto.OrderMessage;
import com.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedListener {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "order.created", groupId = "inventory-group")
    public void listen(OrderMessage orderMessage) {
        System.out.println("📥 Received from order.created: " + orderMessage.getId());
        inventoryService.handleOrder(orderMessage);
    }
}
