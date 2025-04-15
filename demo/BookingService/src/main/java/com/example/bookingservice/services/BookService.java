package com.example.bookingservice.services;

import com.example.bookingservice.dtos.BookDTO;
import com.example.bookingservice.dtos.RatingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookDTO creatBook (BookDTO bookDTO);
    BookDTO updateBook (BookDTO bookDTO);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    BookDTO getBookById(Long id);
    BookDTO getBookByIsbn(String isbn);
    Page<BookDTO> getAllBooks(Pageable pageable);
    List<BookDTO> getBooksByCategory(String category);
    void deleteBook(Long id);
    RatingDTO addRating(Long bookId, RatingDTO ratingDTO,Long userId);
}
