package com.example.orderservice.Feignclients;

import com.example.orderservice.Dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "cart-service", url="${cart.service.url}")
public interface CartServiceClient {
    @GetMapping ("/api/cart/{userId}")
    CartDTO getCart(@PathVariable Long userId);
    @DeleteMapping("/{userId}/clear")
    void clearCart(@PathVariable Long userId);

}
