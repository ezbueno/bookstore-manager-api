package com.buenoezandro.bookstore.publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buenoezandro.bookstore.publisher.service.PublisherService;

@RestController
@RequestMapping(value = "/api/v1/publishers")
public class PublisherController {

	private PublisherService publisherService;

	@Autowired
	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

}
