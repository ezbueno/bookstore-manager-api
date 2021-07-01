package com.buenoezandro.bookstore.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buenoezandro.bookstore.author.mapper.AuthorMapper;
import com.buenoezandro.bookstore.author.repository.AuthorRepository;

@Service
public class AuthorService {

	private AuthorRepository authorRepository;
	private static final AuthorMapper AUTHOR_MAPPER = AuthorMapper.INSTANCE;

	@Autowired
	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

}
