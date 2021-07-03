package com.buenoezandro.bookstore.author.service;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Collections;
import java.util.List;
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
import com.buenoezandro.bookstore.author.exception.AuthorNotFoundException;
import com.buenoezandro.bookstore.author.mapper.AuthorMapper;
import com.buenoezandro.bookstore.author.repository.AuthorRepository;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

	private static final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

	@Mock
	private AuthorRepository authorRepository;

	@InjectMocks
	private AuthorService authorService;

	private AuthorDTOBuilder authorDTOBuilder;

	@BeforeEach
	void setup() {
		this.authorDTOBuilder = AuthorDTOBuilder.builder().build();
	}

	@Test
	void whenNewAuthorIsInformedThenItShouldBeCreated() {
		AuthorDTO expectedAuthorToCreateDTO = this.authorDTOBuilder.buildAuthorDTO();
		Author expectedCreatedAuthor = authorMapper.toModel(expectedAuthorToCreateDTO);

		Mockito.when(this.authorRepository.save(expectedCreatedAuthor)).thenReturn(expectedCreatedAuthor);
		Mockito.when(this.authorRepository.findByName(expectedAuthorToCreateDTO.getName()))
				.thenReturn(Optional.empty());

		AuthorDTO createdAuthorDTO = this.authorService.create(expectedAuthorToCreateDTO);

		MatcherAssert.assertThat(createdAuthorDTO, Is.is(IsEqual.equalTo(expectedAuthorToCreateDTO)));
	}

	@Test
	void whenExistingAuthorIsInformedThenAnExceptionShouldBeThrown() {
		AuthorDTO expectedAuthorToCreateDTO = this.authorDTOBuilder.buildAuthorDTO();
		Author expectedCreatedAuthor = authorMapper.toModel(expectedAuthorToCreateDTO);

		Mockito.when(this.authorRepository.findByName(expectedAuthorToCreateDTO.getName()))
				.thenReturn(Optional.of(expectedCreatedAuthor));

		Assertions.assertThrows(AuthorAlreadyExistsException.class,
				() -> this.authorService.create(expectedAuthorToCreateDTO));
	}

	@Test
	void whenValidIdIsGivenThenAnAuthorShouldBeReturned() {
		AuthorDTO expectedFoundAuthorDTO = this.authorDTOBuilder.buildAuthorDTO();
		Author expectedFoundAuthor = authorMapper.toModel(expectedFoundAuthorDTO);

		Mockito.when(this.authorRepository.findById(expectedFoundAuthorDTO.getId()))
				.thenReturn(Optional.of(expectedFoundAuthor));

		AuthorDTO foundAuthorDTO = this.authorService.findById(expectedFoundAuthorDTO.getId());

		MatcherAssert.assertThat(foundAuthorDTO, Is.is(equalTo(expectedFoundAuthorDTO)));
	}

	@Test
	void whenInvalidIdIsGivenThenAnExceptionShouldBeThrown() {
		AuthorDTO expectedFoundAuthorDTO = this.authorDTOBuilder.buildAuthorDTO();

		Mockito.when(this.authorRepository.findById(expectedFoundAuthorDTO.getId())).thenReturn(Optional.empty());

		Assertions.assertThrows(AuthorNotFoundException.class,
				() -> this.authorService.findById(expectedFoundAuthorDTO.getId()));
	}

	@Test
	void whenListAuthorsIsCalledThenItShouldBeReturned() {
		AuthorDTO expectedFoundAuthorDTO = this.authorDTOBuilder.buildAuthorDTO();
		Author expectedFoundAuthor = authorMapper.toModel(expectedFoundAuthorDTO);

		Mockito.when(this.authorRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundAuthor));

		List<AuthorDTO> foundAuthorsDTO = this.authorService.findAll();

		MatcherAssert.assertThat(foundAuthorsDTO.size(), Is.is(1));
		MatcherAssert.assertThat(foundAuthorsDTO.get(0), Is.is(expectedFoundAuthorDTO));

	}

	@Test
	void whenListAuthorsIsCalledThenAnEmptyListShouldBeReturned() {
		Mockito.when(this.authorRepository.findAll()).thenReturn(Collections.emptyList());

		List<AuthorDTO> foundAuthorsDTO = this.authorService.findAll();

		MatcherAssert.assertThat(foundAuthorsDTO.size(), Is.is(0));
	}

}
