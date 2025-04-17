package com.example.cartservice.Client;

import com.example.cartservice.Dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name="book-service")
public interface BookServiceClient {
    @GetMapping("/books/{id}")
    BookDto getBookById(@PathVariable Long id);
    @PutMapping("/api/books/{id}/stock")
    void updateStock(@PathVariable Long id, @RequestParam int quantity);
}
