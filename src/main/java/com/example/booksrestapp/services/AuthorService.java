package com.example.booksrestapp.services;

import com.example.booksrestapp.models.entities.AuthorEntity;
import com.example.booksrestapp.repositories.AuthorSearchSpecification;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorEntity> getAllAuthors(AuthorSearchSpecification searchSpecification);

    List<AuthorEntity> getAllAuthors();

    Optional<AuthorEntity> getAuthorById(long authorId);

    AuthorEntity saveNewAuthor(AuthorEntity author);

    void deleteAuthorById(Long authorId);
}
