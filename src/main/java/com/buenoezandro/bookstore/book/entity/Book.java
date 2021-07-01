package com.buenoezandro.bookstore.book.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.buenoezandro.bookstore.author.entity.Author;
import com.buenoezandro.bookstore.entity.Auditable;
import com.buenoezandro.bookstore.publisher.entity.Publisher;
import com.buenoezandro.bookstore.user.entity.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Book extends Auditable {

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
