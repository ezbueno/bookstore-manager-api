package com.buenoezandro.bookstore.book.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.buenoezandro.bookstore.author.entity.Author;
import com.buenoezandro.bookstore.publisher.entity.Publisher;
import com.buenoezandro.bookstore.user.entity.User;

import lombok.Data;

@Data
@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false)
	private String isbn;

	@Column(columnDefinition = "Integer default 0")
	private int pages;

	@Column(columnDefinition = "Integer default 0")
	private int chapters;

	@ManyToOne(cascade = { CascadeType.MERGE })
	private Author author;

	@ManyToOne(cascade = { CascadeType.MERGE })
	private Publisher publisher;

	@ManyToOne(cascade = { CascadeType.MERGE })
	private User user;

}
