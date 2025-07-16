package com.shippingservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shippingservice.dto.OrderMessage;
import org.apache.kafka.common.serialization.Deserializer;

public class OrderMessageDeserializer implements Deserializer<OrderMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OrderMessage deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.readValue(data, OrderMessage.class);
        } catch (Exception e) {
            System.out.println("❌ Failed to deserialize message: " + e.getMessage());
            return null;
        }
    }
}

