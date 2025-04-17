package com.example.orderservice.Feignclients;

import com.example.orderservice.Dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "cart-service")
public interface CartServiceClient {
    @GetMapping ("/cart/{userId}")
    CartDTO getCart(@PathVariable Long userId);
    @DeleteMapping("/cart/{userId}/clear")
    void clearCart(@PathVariable Long userId);

}
