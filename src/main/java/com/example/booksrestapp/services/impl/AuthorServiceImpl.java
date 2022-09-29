package com.example.booksrestapp.services.impl;

import com.example.booksrestapp.models.entities.AuthorEntity;
import com.example.booksrestapp.repositories.AuthorRepository;
import com.example.booksrestapp.repositories.AuthorSearchSpecification;
import com.example.booksrestapp.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorEntity> getAllAuthors(AuthorSearchSpecification searchSpecification) {
        return authorRepository.findAll(searchSpecification);
    }

    @Override
    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<AuthorEntity> getAuthorById(long authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    public AuthorEntity saveNewAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthorById(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}
