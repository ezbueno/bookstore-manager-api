package com.buenoezandro.bookstore.publisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buenoezandro.bookstore.publisher.mapper.PublisherMapper;
import com.buenoezandro.bookstore.publisher.repository.PublisherRepository;

@Service
public class PublisherService {

	private PublisherRepository publisherRepository;
	private static final PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

	@Autowired
	public PublisherService(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
	}

}
