package com.orderservice.order.model;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderMessage {
    private String id;
    private String productId;
    private int quantity;
    private OrderStatus status;

    public OrderMessage(String productId, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.productId = productId;
        this.quantity = quantity;
        this.status = OrderStatus.CREATED;
    }
}
