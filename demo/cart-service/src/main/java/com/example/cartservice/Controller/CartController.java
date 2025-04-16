package com.example.cartservice.Controller;

import com.example.cartservice.Entity.Cart;
import com.example.cartservice.Service.CarServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CarServices carServices;

    @GetMapping("/userId")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId){
        return ResponseEntity.ok(carServices.getCartByUserId(userId));
    }
    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addToCart( @PathVariable Long userId,
                                           @RequestParam Long bookId,
                                           @RequestParam(defaultValue = "1") Integer quantity){
        return ResponseEntity.ok(carServices.addToCart(userId,bookId,quantity));
    }
    @DeleteMapping("/{userId}/remove/{itemId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long userId,
                                               @PathVariable Long itemId){
        carServices.removeFromCart(userId,itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId){
        carServices.clearCart(userId);
        return ResponseEntity.noContent().build();
    }

}
