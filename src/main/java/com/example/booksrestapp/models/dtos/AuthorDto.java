package com.example.booksrestapp.models.dtos;


public class AuthorDto {
    private Long id;
    private String name;

    public AuthorDto() {
    }

    public Long getId() {
        return id;
    }

    public AuthorDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthorDto setName(String name) {
        this.name = name;
        return this;
    }
}
