package com.example.booksrestapp.repositories;

import com.example.booksrestapp.models.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long>,
        JpaSpecificationExecutor<AuthorEntity> {
    Optional<AuthorEntity> findByName(String name);
}
