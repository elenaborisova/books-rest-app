package com.example.booksrestapp.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity{
    private String title;
    private String isbn;
    private AuthorEntity author;

    public BookEntity() {
    }

    @Column
    public String getTitle() {
        return title;
    }

    public BookEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    @Column
    public BookEntity setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public AuthorEntity getAuthor() {
        return author;
    }

    public BookEntity setAuthor(AuthorEntity author) {
        this.author = author;
        return this;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author=" + author +
                '}';
    }
}
