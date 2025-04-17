package com.example.cartservice.Repository;

import com.example.cartservice.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository  extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUserId(Long userId);
}
