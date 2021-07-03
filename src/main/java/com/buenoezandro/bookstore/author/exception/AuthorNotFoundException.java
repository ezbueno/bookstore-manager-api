package com.buenoezandro.bookstore.author.exception;

import javax.persistence.EntityNotFoundException;

public class AuthorNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public AuthorNotFoundException(Long id) {
		super(String.format("Author with ID: %s not exists!", id));
	}

}
