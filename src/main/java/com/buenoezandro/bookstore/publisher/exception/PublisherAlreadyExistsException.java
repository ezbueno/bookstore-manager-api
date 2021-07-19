package com.buenoezandro.bookstore.publisher.exception;

import javax.persistence.EntityExistsException;

public class PublisherAlreadyExistsException extends EntityExistsException {

	private static final long serialVersionUID = 1L;

	public PublisherAlreadyExistsException(String name, String code) {
		super(String.format("Publisher with name %s or code %s already exists!", name, code));
	}

}
