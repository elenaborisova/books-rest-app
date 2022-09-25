package com.example.booksrestapp.web;

import com.example.booksrestapp.models.entities.AuthorEntity;
import com.example.booksrestapp.repositories.AuthorRepository;
import com.example.booksrestapp.repositories.AuthorSearchSpecification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/search")
    public List<AuthorEntity> searchAuthors(@RequestParam(required = false, name = "name") String name,
                                            @RequestParam(required = false, name = "book_title") String bookTitle) {
        AuthorSearchSpecification searchSpecification = new AuthorSearchSpecification(
                name,
                bookTitle
        );

        return authorRepository.findAll(searchSpecification);
    }

    @GetMapping
    public List<AuthorEntity> getAuthors() {
        return authorRepository.findAll();
    }
}
