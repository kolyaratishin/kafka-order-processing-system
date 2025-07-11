package com.shippingservice.service;

import com.shippingservice.dto.OrderMessage;
import com.shippingservice.dto.OrderStatus;
import com.shippingservice.kafka.ShippingProducer;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private final ShippingProducer shippingProducer;

    public ShippingService(ShippingProducer shippingProducer) {
        this.shippingProducer = shippingProducer;
    }

    public void handleValidatedOrder(OrderMessage order) {
        System.out.println("📦 Shipping order: " + order.getId());
        try {
            Thread.sleep(500); // імітація затримки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        order.setStatus(OrderStatus.SHIPPED);
        shippingProducer.sendShipped(order);
    }
}
