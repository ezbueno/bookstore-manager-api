package com.buenoezandro.bookstore.author.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buenoezandro.bookstore.author.dto.AuthorDTO;
import com.buenoezandro.bookstore.author.service.AuthorService;

@RestController
@RequestMapping(value = "/api/v1/authors")
public class AuthorController implements AuthorControllerDocs {

	private AuthorService authorService;

	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AuthorDTO findById(@PathVariable Long id) {
		return this.authorService.findById(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AuthorDTO> findAll() {
		return this.authorService.findAll();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public AuthorDTO create(@Valid @RequestBody AuthorDTO authorDTO) {
		return authorService.create(authorDTO);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.authorService.delete(id);
	}

}
