package com.buenoezandro.bookstore.author.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.bookstore.author.dto.AuthorDTO;
import com.buenoezandro.bookstore.author.entity.Author;
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
		var foundAuthor = this.verifyAndGetAuthor(id);
		return authorMapper.toDTO(foundAuthor);
	}

	@Transactional(readOnly = true)
	public List<AuthorDTO> findAll() {
		return this.authorRepository.findAll().stream().map(authorMapper::toDTO).collect(Collectors.toList());
	}

	@Transactional
	public AuthorDTO create(AuthorDTO authorDTO) {
		this.verifyIfExists(authorDTO.getName());
		var authorToCreate = authorMapper.toModel(authorDTO);
		var createdAuthor = this.authorRepository.save(authorToCreate);
		return authorMapper.toDTO(createdAuthor);
	}

	@Transactional
	public void delete(Long id) {
		this.verifyAndGetAuthor(id);
		this.authorRepository.deleteById(id);
	}

	private Author verifyAndGetAuthor(Long id) {
		return this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
	}

	private void verifyIfExists(String authorName) {
		this.authorRepository.findByName(authorName).ifPresent(author -> {
			throw new AuthorAlreadyExistsException(authorName);
		});
	}

}
