package com.example.booksrestapp.services.impl;

import com.example.booksrestapp.models.dtos.AuthorDto;
import com.example.booksrestapp.models.dtos.BookDto;
import com.example.booksrestapp.models.entities.AuthorEntity;
import com.example.booksrestapp.models.entities.BookEntity;
import com.example.booksrestapp.repositories.AuthorRepository;
import com.example.booksrestapp.repositories.BookRepository;
import com.example.booksrestapp.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::asBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDto> getBookById(Long id) {
        return bookRepository
                .findById(id)
                .map(this::asBookDto);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Long createBook(BookDto bookDto) {
        AuthorEntity author = authorRepository
                .findByName(bookDto.getAuthor().getName())
                .orElseGet(() -> new AuthorEntity().setName(bookDto.getAuthor().getName()));

        BookEntity book = new BookEntity()
                .setAuthor(author)
                .setIsbn(bookDto.getIsbn())
                .setTitle(bookDto.getTitle());

        return bookRepository.save(book).getId();
    }

    private BookDto asBookDto(BookEntity book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        AuthorDto authorDto = modelMapper.map(book.getAuthor(), AuthorDto.class);
        bookDto.setAuthor(authorDto);
        return bookDto;
    }
}
