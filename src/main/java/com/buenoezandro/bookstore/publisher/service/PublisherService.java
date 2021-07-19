package com.buenoezandro.bookstore.publisher.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.bookstore.publisher.dto.PublisherDTO;
import com.buenoezandro.bookstore.publisher.entity.Publisher;
import com.buenoezandro.bookstore.publisher.exception.PublisherAlreadyExistsException;
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

	@Transactional
	public PublisherDTO create(PublisherDTO publisherDTO) {
		this.verifyIfExists(publisherDTO.getName(), publisherDTO.getCode());

		var publisherToCreate = publisherMapper.toModel(publisherDTO);
		var createdPublisher = this.publisherRepository.save(publisherToCreate);
		return publisherMapper.toDTO(createdPublisher);
	}

	private void verifyIfExists(String name, String code) {
		Optional<Publisher> duplicatedPublisher = this.publisherRepository.findByNameOrCode(name, code);

		if (duplicatedPublisher.isPresent()) {
			throw new PublisherAlreadyExistsException(name, code);
		}
	}

}
