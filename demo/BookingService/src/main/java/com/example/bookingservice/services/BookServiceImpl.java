package com.example.bookingservice.services;

import com.example.bookingservice.Entites.Book;
import com.example.bookingservice.dtos.BookDTO;
import com.example.bookingservice.dtos.RatingDTO;
import com.example.bookingservice.exceptions.ResourceNotFoundException;
import com.example.bookingservice.repositories.BookRpositories;
import com.example.bookingservice.repositories.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRpositories bookRpositories;
    private final RatingRepository ratingRepository;
    private final ModelMapper modelMapper;


    @Override
    public BookDTO creatBook(BookDTO bookDTO) {
        Book book= modelMapper.map(bookDTO, Book.class);
        Book savedBook= bookRpositories.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }



    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRpositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        if (!existingBook.getIsbn().equals(bookDTO.getIsbn())) {
            throw new IllegalArgumentException("ISBN cannot be modified");
        }

        modelMapper.map(bookDTO, existingBook);
        Book updatedBook = bookRpositories.save(existingBook);
        return modelMapper.map(updatedBook, BookDTO.class);
    }


    @Override
    public BookDTO getBookById(Long id){
        Book book = bookRpositories.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Book not found with id "+id));
        return modelMapper.map(book, BookDTO.class);
    }



    @Override
    public BookDTO getBookByIsbn(String isbn) {
        Book book = bookRpositories.findByIsbn(isbn).orElseThrow(()->new ResourceNotFoundException("Book not foundExceptions"));
        return modelMapper.map(book,BookDTO.class);
    }

    @Override
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        return bookRpositories.findAll(pageable).map(book -> modelMapper.map(book, BookDTO.class));
    }

    @Override
    public List<BookDTO> getBooksByCategory(String category) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public RatingDTO addRating(Long bookId, RatingDTO ratingDTO, Long userId) {
        return null;
    }
}
