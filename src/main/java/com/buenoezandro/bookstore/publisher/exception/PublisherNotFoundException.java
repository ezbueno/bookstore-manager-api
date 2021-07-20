package com.buenoezandro.bookstore.publisher.exception;

import javax.persistence.EntityNotFoundException;

public class PublisherNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PublisherNotFoundException(Long id) {
		super(String.format("Publisher with id %s not exists!", id));
	}

}
