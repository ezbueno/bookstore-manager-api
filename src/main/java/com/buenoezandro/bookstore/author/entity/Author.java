package com.buenoezandro.bookstore.author.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.buenoezandro.bookstore.book.entity.Book;

import lombok.Data;

@Data
@Entity
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(columnDefinition = "Integer default 0")
	private int age;

	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	private List<Book> books;

}
