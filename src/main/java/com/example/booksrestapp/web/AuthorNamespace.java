package com.example.booksrestapp.web;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AuthorNamespace.URI_AUTHORS)
public interface AuthorNamespace {
    String URI_AUTHORS="/authors";
}
