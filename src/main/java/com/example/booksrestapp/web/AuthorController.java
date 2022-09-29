package com.example.booksrestapp.web;

import com.example.booksrestapp.models.entities.AuthorEntity;
import com.example.booksrestapp.repositories.AuthorSearchSpecification;
import com.example.booksrestapp.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/search")
    public List<AuthorEntity> searchAuthors(@RequestParam(required = false, name = "name") String name,
                                            @RequestParam(required = false, name = "book_title") String bookTitle) {
        AuthorSearchSpecification searchSpecification = new AuthorSearchSpecification(
                name,
                bookTitle
        );

        return authorService.getAllAuthors(searchSpecification);
    }

    @GetMapping
    public List<AuthorEntity> getAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorEntity> getAuthor(@PathVariable long authorId) {

        Optional<AuthorEntity> author = authorService.getAuthorById(authorId);

        return author
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuthorEntity> create(UriComponentsBuilder ucBuilder,
                                               @RequestBody AuthorEntity author) {

        AuthorEntity newAuthor = authorService.saveNewAuthor(author);

        return ResponseEntity
                .created(ucBuilder.path("/authors/{authorId}")
                        .buildAndExpand(newAuthor.getId()).toUri()).build();
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<AuthorEntity> delete(@PathVariable Long authorId) {
        authorService.deleteAuthorById(authorId);
        return ResponseEntity.noContent().build();
    }
}
