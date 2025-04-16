package com.example.cartservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CartEventProducer {

 private static final String CART_UPDATED_TOPIC="cart-updated";
 @Autowired
    private KafkaTemplate<String , String > kafkaTemplate;
 public void sendCartUpdatedEvent(Long userId){
     kafkaTemplate.send(CART_UPDATED_TOPIC,userId.toString(),
                          "Cart updated for user "+ userId);
 }
}
