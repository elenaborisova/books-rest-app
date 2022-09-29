package com.example.booksrestapp.web;

import com.example.booksrestapp.models.entities.AuthorEntity;
import com.example.booksrestapp.repositories.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthorRepository authorRepository;

    private long TEST_AUTHOR1_ID, TEST_AUTHOR2_ID;
    private String TEST_AUTHOR1_NAME = "James", TEST_AUTHOR2_NAME = "Nick";

    @BeforeEach
    public void setUp() {
        authorRepository.deleteAll();

        AuthorEntity author1 = new AuthorEntity()
                .setName(TEST_AUTHOR1_NAME);
        author1 = authorRepository.save(author1);
        TEST_AUTHOR1_ID = author1.getId();

        AuthorEntity author2 = new AuthorEntity()
                .setName(TEST_AUTHOR2_NAME);
        author2 = authorRepository.save(author2);
        TEST_AUTHOR2_ID = author2.getId();
    }

    @AfterEach
    public void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    public void testAuthorsReturnsCorrectStatusCode() throws Exception {
        this.mockMvc.perform(get("/authors"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAuthorNotFound() throws Exception {
        this.mockMvc.perform(get("/authors/{id}", 666))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAuthorFound() throws Exception {
        this.mockMvc.perform(get("/authors/{id}", TEST_AUTHOR1_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(TEST_AUTHOR1_NAME)));
    }

    @Test
    public void testAllAuthors() throws Exception {
        this.mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].name", is(TEST_AUTHOR1_NAME)))
                .andExpect(jsonPath("$.[1].name", is(TEST_AUTHOR2_NAME)))
                .andExpect(jsonPath("$.[0].id", is((int)TEST_AUTHOR1_ID)))
                .andExpect(jsonPath("$.[1].id", is((int)TEST_AUTHOR2_ID)));
    }

}
