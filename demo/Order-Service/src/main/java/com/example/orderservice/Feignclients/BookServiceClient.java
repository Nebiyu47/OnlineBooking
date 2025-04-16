package com.example.orderservice.Feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="book-service", path="/api/books")
public interface BookServiceClient {
    @PutMapping("/{id}/stock")
    void updateStock (@PathVariable Long id, @RequestParam int quantity);
}
