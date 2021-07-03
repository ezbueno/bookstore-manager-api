package com.buenoezandro.bookstore.author.controller;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.buenoezandro.bookstore.author.builder.AuthorDTOBuilder;
import com.buenoezandro.bookstore.author.dto.AuthorDTO;
import com.buenoezandro.bookstore.author.service.AuthorService;
import com.buenoezandro.bookstore.utils.JsonConversionUtils;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

	private static final String AUTHOR_API_URL_PATH = "/api/v1/authors";

	@Mock
	private AuthorService authorService;

	@InjectMocks
	private AuthorController authorController;

	private MockMvc mockMvc;

	private AuthorDTOBuilder authorDTOBuilder;

	@BeforeEach
	void setup() {
		this.authorDTOBuilder = AuthorDTOBuilder.builder().build();
		mockMvc = MockMvcBuilders.standaloneSetup(this.authorController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((s, locale) -> new MappingJackson2JsonView()).build();
	}

	@Test
	void whenPOSTIsCalledThenStatusCreatedShouldBeReturned() throws Exception {
		AuthorDTO expectedCreatedAuthorDTO = this.authorDTOBuilder.buildAuthorDTO();

		Mockito.when(this.authorService.create(expectedCreatedAuthorDTO)).thenReturn(expectedCreatedAuthorDTO);

		mockMvc.perform(MockMvcRequestBuilders.post(AUTHOR_API_URL_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(JsonConversionUtils.asJsonString(expectedCreatedAuthorDTO)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(expectedCreatedAuthorDTO.getId().intValue())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is(expectedCreatedAuthorDTO.getName())))
				.andExpect((MockMvcResultMatchers.jsonPath("$.age", Is.is(expectedCreatedAuthorDTO.getAge()))));
	}

	@Test
	void whenPOSTIsCalledWithoutRequiredFieldThenBadRequestShouldBeInformed() throws Exception {
		AuthorDTO expectedCreatedAuthorDTO = this.authorDTOBuilder.buildAuthorDTO();
		expectedCreatedAuthorDTO.setName(null);

		mockMvc.perform(MockMvcRequestBuilders.post(AUTHOR_API_URL_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(JsonConversionUtils.asJsonString(expectedCreatedAuthorDTO)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void whenGETWithValidIdIsCalledThenStatusOkShouldBeReturned() throws Exception {
		AuthorDTO expectedFoundAuthorDTO = this.authorDTOBuilder.buildAuthorDTO();

		Mockito.when(this.authorService.findById(expectedFoundAuthorDTO.getId())).thenReturn(expectedFoundAuthorDTO);

		mockMvc.perform(MockMvcRequestBuilders.get(AUTHOR_API_URL_PATH + "/" + expectedFoundAuthorDTO.getId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(expectedFoundAuthorDTO.getId().intValue())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is(expectedFoundAuthorDTO.getName())))
				.andExpect((MockMvcResultMatchers.jsonPath("$.age", Is.is(expectedFoundAuthorDTO.getAge()))));
	}

}
