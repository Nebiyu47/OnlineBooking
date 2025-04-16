package com.example.bookingservice.repositories;

import com.example.bookingservice.Entites.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRpositories extends JpaRepository<Book,Long> {
    List<Book> findByCategory(String category);
    List<Book> findByTitleContainingOrDescriptionContaining(String title, String description);


}
