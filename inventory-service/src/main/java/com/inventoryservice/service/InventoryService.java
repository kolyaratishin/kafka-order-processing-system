package com.inventoryservice.service;

import com.inventoryservice.dto.OrderMessage;
import com.inventoryservice.dto.OrderStatus;
import com.inventoryservice.kafka.InventoryProducer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final Map<String, Integer> stock = new HashMap<>();
    private final InventoryProducer producer;

    public InventoryService(InventoryProducer producer) {
        this.producer = producer;
        stock.put("product-123", 10);
        stock.put("product-456", 0); // товару немає
    }

    public void handleOrder(OrderMessage order) {
        int available = stock.getOrDefault(order.getProductId(), 0);
        if (available >= order.getQuantity()) {
            order.setStatus(OrderStatus.VALIDATED);
            producer.sendValidated(order);
        } else {
            order.setStatus(OrderStatus.REJECTED);
            producer.sendRejected(order);
        }
    }
}
