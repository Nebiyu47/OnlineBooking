package com.example.cartservice.Repository;

import com.example.cartservice.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart,Long> {
}
