package com.example.orderservice.Controller;

import com.example.orderservice.Entity.Order;
import com.example.orderservice.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.createOrder(userId));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>>getOrdersByUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
