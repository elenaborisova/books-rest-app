package com.example.booksrestapp.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity{

    private String name;
    private List<BookEntity> books = new LinkedList<>();

    public AuthorEntity() {
    }

    @Column
    public String getName() {
        return name;
    }

    public AuthorEntity setName(String name) {
        this.name = name;
        return this;
    }

    @OneToMany(mappedBy = "author")
    public List<BookEntity> getBooks() {
        return books;
    }

    public AuthorEntity setBooks(List<BookEntity> books) {
        this.books = books;
        return this;
    }

    public AuthorEntity addBook(BookEntity book) {
        this.books.add(book);
        return this;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
