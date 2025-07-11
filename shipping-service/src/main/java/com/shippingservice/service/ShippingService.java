package com.shippingservice.service;

import com.shippingservice.dto.OrderMessage;
import com.shippingservice.dto.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public OrderMessage shipOrder(OrderMessage order) {
        System.out.println("Shipping order: " + order.getId());
        // Симуляція доставки
        try {
            Thread.sleep(500); // затримка 0.5с
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        order.setStatus(OrderStatus.SHIPPED);
        return order;
    }
}
