package com.example.orderservice.Service;

import com.example.orderservice.Entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component

public class OrderEventProducer {

     private static final String ORDER_CREATED_TOPIC="order-created";
    @Autowired
    private KafkaTemplate<String , String> kafkaTemplate;
     @Autowired
    private ObjectMapper objectMapper;
     public void sendOrderCreatedEvent(Order order){
         try {
             String orderJson = objectMapper.writeValueAsString(order);
             kafkaTemplate.send(ORDER_CREATED_TOPIC,order.getUserId().toString(),orderJson);
         }catch (JsonProcessingException e){
             throw new RuntimeException("Error serializing order", e);
         }
     }
}
