package com.shippingservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shippingservice.dto.OrderMessage;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class OrderMessageSerializer implements Serializer<OrderMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, OrderMessage data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("❌ Error serializing OrderMessage", e);
        }
    }
}

