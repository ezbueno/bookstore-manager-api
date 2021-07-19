package com.buenoezandro.bookstore.publisher.service;

import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buenoezandro.bookstore.publisher.builder.PublisherDTOBuilder;
import com.buenoezandro.bookstore.publisher.exception.PublisherAlreadyExistsException;
import com.buenoezandro.bookstore.publisher.mapper.PublisherMapper;
import com.buenoezandro.bookstore.publisher.repository.PublisherRepository;

@ExtendWith(MockitoExtension.class)
class PublisherServiceTest {

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

	@Test
	void whenNewPublisherIsInformedThenItShouldBeCreated() {
		var expectedPublisherToCreateDTO = this.publisherDTOBuilder.buildPublisherDTO();
		var expectedPublisherCreated = this.publisherMapper.toModel(expectedPublisherToCreateDTO);

		Mockito.when(this.publisherRepository.findByNameOrCode(expectedPublisherToCreateDTO.getName(),
				expectedPublisherToCreateDTO.getCode())).thenReturn(Optional.empty());

		Mockito.when(this.publisherRepository.save(expectedPublisherCreated)).thenReturn(expectedPublisherCreated);

		var createdPublisherDTO = this.publisherService.create(expectedPublisherToCreateDTO);

		MatcherAssert.assertThat(createdPublisherDTO, Matchers.is(Matchers.equalTo(expectedPublisherToCreateDTO)));
	}

	@Test
	void whenExistingPublisherIsInformedThenAnExceptionShouldBeThrown() {
		var expectedPublisherToCreateDTO = this.publisherDTOBuilder.buildPublisherDTO();
		var expectedPublisherDuplicated = this.publisherMapper.toModel(expectedPublisherToCreateDTO);

		Mockito.when(this.publisherRepository.findByNameOrCode(expectedPublisherToCreateDTO.getName(),
				expectedPublisherToCreateDTO.getCode())).thenReturn(Optional.of(expectedPublisherDuplicated));

		Assertions.assertThrows(PublisherAlreadyExistsException.class,
				() -> this.publisherService.create(expectedPublisherToCreateDTO));
	}
}
