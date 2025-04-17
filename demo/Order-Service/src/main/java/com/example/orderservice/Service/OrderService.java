package com.example.orderservice.Service;

import com.example.orderservice.Dto.CartDTO;
import com.example.orderservice.Entity.Order;
import com.example.orderservice.Entity.OrderItem;
import com.example.orderservice.Exceptions.EmptyCartException;
import com.example.orderservice.Exceptions.OrderNotFoundException;
import com.example.orderservice.Feignclients.BookServiceClient;
import com.example.orderservice.Feignclients.CartServiceClient;
import com.example.orderservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;




@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {


    private final OrderRepository orderRepository;
    private final CartServiceClient cartServiceClient;
    private final BookServiceClient bookServiceClient;
    private final OrderEventProducer orderEventProducer;
    @Transactional
    public Order createOrder(Long userId){
        log.info("Fetching cart for user: {}", userId);
        CartDTO cart;
        try {
            cart = cartServiceClient.getCart(userId);
        } catch (Exception e) {
            log.error("Failed to fetch cart for user: {}", userId, e);
            throw new EmptyCartException("Failed to fetch cart for user: " + userId);
        }

        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            log.warn("Cart is empty for user: {}", userId);
            throw new EmptyCartException("Cannot create order from empty cart.");
        }

        Order order = new Order();
        order.setUserId(userId);

        cart.getItems().forEach(cartItemDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(cartItemDto.getBookId());
            orderItem.setBookTitle(cartItemDto.getBookTitle());
            orderItem.setPrice(cartItemDto.getPrice());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
        });

        order.setTotalAmount(cart.getTotal());

        Order savedOrder = orderRepository.save(order);
        log.info("Order saved with ID: {}", savedOrder.getId());

        try {
            cartServiceClient.clearCart(userId);
            log.info("Cart cleared for user: {}", userId);
        } catch (Exception e) {
            log.error("Failed to clear cart for user: {}", userId, e);
        }

        orderEventProducer.sendOrderCreatedEvent(savedOrder);
        log.info("Order event sent for order ID: {}", savedOrder.getId());

        return savedOrder;
    }
   public List<Order> getOrdersByUserId(Long userId){
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId);
   }
   public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(()->
                new OrderNotFoundException("Order Not Found Exceptions"+ id)
                );
   }
}
