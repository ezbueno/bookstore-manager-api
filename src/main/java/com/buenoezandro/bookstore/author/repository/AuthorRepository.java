package com.buenoezandro.bookstore.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buenoezandro.bookstore.author.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
