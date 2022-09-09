package com.example.booksrestapp.services;

import com.example.booksrestapp.models.dtos.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks();

    Optional<BookDto> getBookById(Long id);

    void deleteBook(Long id);

    Long createBook(BookDto bookDto);
}
