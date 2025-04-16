package com.example.notificationservice.Service;

import com.example.notificationservice.Dto.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;
    @KafkaListener(topics = "order-created",groupId = "notification-group")
    public void consumeOrderCreatedEvent(String orderJson){
        try {
            OrderDto order = objectMapper.readValue(orderJson, OrderDto.class);
            notificationService.createNotification(
                    order.getUserId(),
                    "Order Confirimation",
                    "Your order "+order.getId()+"has been recived",
                    "ORDER_CONFIRMATION"
            );

        }catch (Exception e){
           System.err.println("Error processing order event:"+e.getMessage());
        }
    }

}
