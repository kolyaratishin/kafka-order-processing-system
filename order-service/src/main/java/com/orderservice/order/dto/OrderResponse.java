package com.orderservice.order.dto;

import com.orderservice.order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String id;
    private String productId;
    private int quantity;
    private OrderStatus status;
}
