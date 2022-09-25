package com.example.booksrestapp.repositories;

import com.example.booksrestapp.models.entities.AuthorEntity;
import com.example.booksrestapp.models.entities.BookEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class AuthorSearchSpecification implements Specification<AuthorEntity> {

    private String authorName;
    private String bookTitle;

    public AuthorSearchSpecification(String authorName, String bookTitle) {
        this.authorName = authorName;
        this.bookTitle = bookTitle;
    }

    @Override
    public Predicate toPredicate(Root<AuthorEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate p = criteriaBuilder.conjunction();

        if (authorName != null) {
            p.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder.equal(root.get("name"), authorName))
            );
        }

        if (bookTitle != null) {
            Subquery<BookEntity> bookEntitySubquery = query.subquery(BookEntity.class);
            Root<BookEntity> rootSubquery = bookEntitySubquery.from(BookEntity.class);
            bookEntitySubquery.select(rootSubquery);

            bookEntitySubquery.where(
                    criteriaBuilder.equal(root, rootSubquery.get("author")),
                    criteriaBuilder.equal(rootSubquery.get("title"), bookTitle)
            );

            p.getExpressions().add(criteriaBuilder.exists(bookEntitySubquery));
        }

        return p;
    }
}
