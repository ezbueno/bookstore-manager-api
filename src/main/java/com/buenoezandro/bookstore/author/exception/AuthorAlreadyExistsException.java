package com.buenoezandro.bookstore.author.exception;

import javax.persistence.EntityExistsException;

public class AuthorAlreadyExistsException extends EntityExistsException {

	private static final long serialVersionUID = 1L;

	public AuthorAlreadyExistsException(String name) {
		super(String.format("User with name %s already exists!", name));
	}

}
