package com.buenoezandro.bookstore.publisher.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buenoezandro.bookstore.publisher.builder.PublisherDTOBuilder;
import com.buenoezandro.bookstore.publisher.mapper.PublisherMapper;
import com.buenoezandro.bookstore.publisher.repository.PublisherRepository;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceTest {

	private final PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

	@Mock
	private PublisherRepository publisherRepository;

	@InjectMocks
	private PublisherService publisherService;

	private PublisherDTOBuilder publisherDTOBuilder;

	@BeforeEach
	void setup() {
		this.publisherDTOBuilder = PublisherDTOBuilder.builder().build();
	}
}
