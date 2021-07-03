package com.buenoezandro.bookstore.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.bookstore.author.dto.AuthorDTO;
import com.buenoezandro.bookstore.author.exception.AuthorAlreadyExistsException;
import com.buenoezandro.bookstore.author.exception.AuthorNotFoundException;
import com.buenoezandro.bookstore.author.mapper.AuthorMapper;
import com.buenoezandro.bookstore.author.repository.AuthorRepository;

@Service
public class AuthorService {

	private AuthorRepository authorRepository;
	private static final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

	@Autowired
	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Transactional(readOnly = true)
	public AuthorDTO findById(Long id) {
		var foundAuthor = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
		return authorMapper.toDTO(foundAuthor);
	}

	@Transactional
	public AuthorDTO create(AuthorDTO authorDTO) {
		verifyIfExists(authorDTO.getName());
		var authorToCreate = authorMapper.toModel(authorDTO);
		var createdAuthor = this.authorRepository.save(authorToCreate);
		return authorMapper.toDTO(createdAuthor);
	}

	private void verifyIfExists(String authorName) {
		this.authorRepository.findByName(authorName).ifPresent(author -> {
			throw new AuthorAlreadyExistsException(authorName);
		});
	}

}
