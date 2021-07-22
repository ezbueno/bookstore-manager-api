package com.buenoezandro.bookstore.publisher.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buenoezandro.bookstore.publisher.dto.PublisherDTO;
import com.buenoezandro.bookstore.publisher.service.PublisherService;

@RestController
@RequestMapping(value = "/api/v1/publishers")
public class PublisherController implements PublisherControllerDocs {

	private PublisherService publisherService;

	@Autowired
	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PublisherDTO findById(@PathVariable Long id) {
		return this.publisherService.findById(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PublisherDTO> findAll() {
		return this.publisherService.findAll();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public PublisherDTO create(@RequestBody @Valid PublisherDTO publisherDTO) {
		return this.publisherService.create(publisherDTO);
	}

}
