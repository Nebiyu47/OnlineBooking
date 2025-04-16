package com.example.orderservice.Service;

import com.example.orderservice.Dto.CartDTO;
import com.example.orderservice.Entity.Order;
import com.example.orderservice.Entity.OrderItem;
import com.example.orderservice.Exceptions.OrderNotFoundException;
import com.example.orderservice.Feignclients.BookServiceClient;
import com.example.orderservice.Feignclients.CartServiceClient;
import com.example.orderservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final CartServiceClient cartServiceClient;
    private final BookServiceClient bookServiceClient;
    private final OrderEventProducer orderEventProducer;
    @Transactional
    public Order createOrder(Long userId){
        CartDTO cart= cartServiceClient.getCart(userId);
        Order order = new Order();
        order.setUserId(userId);
        cart.getItems().forEach(cartItemDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBookId(cartItemDto.getNookId());
            orderItem.setBookTitle(cartItemDto.getBookTittle());
            orderItem.setPrice(cartItemDto.getPrice());
            orderItem.setQuantity(cartItemDto.getQuantity());
            order.getItems().add(orderItem);
        });
        order.setTotalAmount(cart.getTotal());
        Order savedOrder = orderRepository.save(order);
        cartServiceClient.clearCart(userId);
        orderEventProducer.sendOrderCreatedEvent(savedOrder);
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
