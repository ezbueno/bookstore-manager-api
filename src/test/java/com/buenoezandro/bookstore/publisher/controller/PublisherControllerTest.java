package com.buenoezandro.bookstore.publisher.controller;

import java.util.Collections;

import org.hamcrest.Matchers;
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

import com.buenoezandro.bookstore.publisher.builder.PublisherDTOBuilder;
import com.buenoezandro.bookstore.publisher.service.PublisherService;
import com.buenoezandro.bookstore.utils.JsonConversionUtils;

@ExtendWith(MockitoExtension.class)
class PublisherControllerTest {

	private static final String PUBLISHERS_API_URL_PATH = "/api/v1/publishers";

	private MockMvc mockMvc;

	@Mock
	private PublisherService publisherService;

	@InjectMocks
	private PublisherController publisherController;

	private PublisherDTOBuilder publisherDTOBuilder;

	@BeforeEach
	void setup() {
		this.publisherDTOBuilder = PublisherDTOBuilder.builder().build();
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.publisherController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((s, locale) -> new MappingJackson2JsonView()).build();
	}

	@Test
	void whenPOSTIsCalledThenCreatedStatusShouldBeInformed() throws Exception {
		var expectedCreatedPublisherDTO = this.publisherDTOBuilder.buildPublisherDTO();

		Mockito.when(this.publisherService.create(expectedCreatedPublisherDTO)).thenReturn(expectedCreatedPublisherDTO);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post(PUBLISHERS_API_URL_PATH).contentType(MediaType.APPLICATION_JSON)
						.content(JsonConversionUtils.asJsonString(expectedCreatedPublisherDTO)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(expectedCreatedPublisherDTO.getId().intValue())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(expectedCreatedPublisherDTO.getName())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(expectedCreatedPublisherDTO.getCode())));
	}

	@Test
	void whenPOSTIsCalledWithoutRequiredFieldsThenBadRequestStatusShouldBeInformed() throws Exception {
		var expectedCreatedPublisherDTO = this.publisherDTOBuilder.buildPublisherDTO();
		expectedCreatedPublisherDTO.setName(null);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post(PUBLISHERS_API_URL_PATH).contentType(MediaType.APPLICATION_JSON)
						.content(JsonConversionUtils.asJsonString(expectedCreatedPublisherDTO)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void whenGETWithValidIdIsCalledThenStatusOkShouldBeInformed() throws Exception {
		var expectedCreatedPublisherDTO = this.publisherDTOBuilder.buildPublisherDTO();
		var expectedCreatedPublisherDTOId = expectedCreatedPublisherDTO.getId();

		Mockito.when(this.publisherService.findById(expectedCreatedPublisherDTOId))
				.thenReturn(expectedCreatedPublisherDTO);

		this.mockMvc
				.perform(MockMvcRequestBuilders.get(PUBLISHERS_API_URL_PATH + "/" + expectedCreatedPublisherDTOId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(expectedCreatedPublisherDTOId.intValue())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(expectedCreatedPublisherDTO.getName())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(expectedCreatedPublisherDTO.getCode())));
	}

	@Test
	void whenGETListIsCalledThenStatusOkShouldBeInformed() throws Exception {
		var expectedCreatedPublisherDTO = this.publisherDTOBuilder.buildPublisherDTO();

		Mockito.when(this.publisherService.findAll())
				.thenReturn(Collections.singletonList(expectedCreatedPublisherDTO));

		this.mockMvc
				.perform(MockMvcRequestBuilders.get(PUBLISHERS_API_URL_PATH).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(expectedCreatedPublisherDTO.getId().intValue())))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(expectedCreatedPublisherDTO.getName())))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(expectedCreatedPublisherDTO.getCode())));
	}

}
