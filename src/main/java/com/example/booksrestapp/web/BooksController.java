package com.example.booksrestapp.web;

import com.example.booksrestapp.models.dtos.BookDto;
import com.example.booksrestapp.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> allBooks = bookService.getAllBooks();

        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookId(@PathVariable("id") Long id) {
        Optional<BookDto> bookDto = bookService.getBookById(id);

        if (bookDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookDto.get());
    }


}
