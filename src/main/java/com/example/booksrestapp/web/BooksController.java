package com.example.booksrestapp.web;

import com.example.booksrestapp.models.dtos.BookDto;
import com.example.booksrestapp.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto,
                                              UriComponentsBuilder uriComponentsBuilder) {
        Long bookId = bookService.createBook(bookDto);

        URI location = uriComponentsBuilder
                .path("/books/{id}")
                .buildAndExpand(bookId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
