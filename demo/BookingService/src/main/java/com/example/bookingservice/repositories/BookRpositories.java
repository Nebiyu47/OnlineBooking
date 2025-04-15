package com.example.bookingservice.repositories;

import com.example.bookingservice.Entites.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRpositories extends JpaRepository<Book,Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByTitileContainingIgnoreCase(String title);
    List<Book>findByCategoriesContaining(String category);
    Page<Book>findAll(Pageable pageable);
}
