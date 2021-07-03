package com.buenoezandro.bookstore.author.service;

import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buenoezandro.bookstore.author.builder.AuthorDTOBuilder;
import com.buenoezandro.bookstore.author.dto.AuthorDTO;
import com.buenoezandro.bookstore.author.entity.Author;
import com.buenoezandro.bookstore.author.exception.AuthorAlreadyExistsException;
import com.buenoezandro.bookstore.author.mapper.AuthorMapper;
import com.buenoezandro.bookstore.author.repository.AuthorRepository;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

	private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

	@Mock
	private AuthorRepository authorRepository;

	@InjectMocks
	private AuthorService authorService;

	private AuthorDTOBuilder authorDTOBuilder;

	@BeforeEach
	void setup() {
		authorDTOBuilder = AuthorDTOBuilder.builder().build();
	}

	@Test
	void whenNewAuthorIsInformedThenItShouldBeCreated() {
		AuthorDTO expectedAuthorToCreateDTO = authorDTOBuilder.buildAuthorDTO();
		Author expectedCreatedAuthor = authorMapper.toModel(expectedAuthorToCreateDTO);

		Mockito.when(authorRepository.save(expectedCreatedAuthor)).thenReturn(expectedCreatedAuthor);
		Mockito.when(authorRepository.findByName(expectedAuthorToCreateDTO.getName())).thenReturn(Optional.empty());

		AuthorDTO createdAuthorDTO = authorService.create(expectedAuthorToCreateDTO);

		MatcherAssert.assertThat(createdAuthorDTO, Is.is(IsEqual.equalTo(expectedAuthorToCreateDTO)));
	}

	@Test
	void whenExistingAuthorIsInformedThenAnExceptionShouldBeThrown() {
		AuthorDTO expectedAuthorToCreateDTO = authorDTOBuilder.buildAuthorDTO();
		Author expectedCreatedAuthor = authorMapper.toModel(expectedAuthorToCreateDTO);

		Mockito.when(authorRepository.findByName(expectedAuthorToCreateDTO.getName()))
				.thenReturn(Optional.of(expectedCreatedAuthor));

		Assertions.assertThrows(AuthorAlreadyExistsException.class,
				() -> authorService.create(expectedAuthorToCreateDTO));
	}

}
