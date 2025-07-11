package com.inventoryservice.service;

import com.inventoryservice.dto.OrderMessage;
import com.inventoryservice.dto.OrderStatus;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final Map<String, Integer> stock = new HashMap<>();

    public InventoryService() {
        stock.put("product-123", 10);
        stock.put("product-456", 0); // симулюємо відсутність
    }

    public OrderMessage checkInventory(OrderMessage order) {
        int available = stock.getOrDefault(order.getProductId(), 0);
        if (available >= order.getQuantity()) {
            order.setStatus(OrderStatus.VALIDATED);
        } else {
            order.setStatus(OrderStatus.REJECTED);
        }
        return order;
    }
}
