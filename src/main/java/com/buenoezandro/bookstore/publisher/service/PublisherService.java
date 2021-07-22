package com.buenoezandro.bookstore.publisher.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.bookstore.publisher.dto.PublisherDTO;
import com.buenoezandro.bookstore.publisher.entity.Publisher;
import com.buenoezandro.bookstore.publisher.exception.PublisherAlreadyExistsException;
import com.buenoezandro.bookstore.publisher.exception.PublisherNotFoundException;
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

	@Transactional(readOnly = true)
	public PublisherDTO findById(Long id) {
		return this.publisherRepository.findById(id).map(publisherMapper::toDTO)
				.orElseThrow(() -> new PublisherNotFoundException(id));
	}

	@Transactional(readOnly = true)
	public List<PublisherDTO> findAll() {
		return this.publisherRepository.findAll().stream().map(publisherMapper::toDTO).collect(Collectors.toList());
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
