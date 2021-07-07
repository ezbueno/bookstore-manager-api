package com.buenoezandro.bookstore.publisher.builder;

import java.time.LocalDate;

import com.buenoezandro.bookstore.publisher.dto.PublisherDTO;

import lombok.Builder;

@Builder
public class PublisherDTOBuilder {

	@Builder.Default
	private final Long id = 1L;

	@Builder.Default
	private final String name = "Bueno Editora";

	@Builder.Default
	private final String code = "BUENO1234";

	@Builder.Default
	private final LocalDate foundationDate = LocalDate.of(2021, 7, 7);

	public PublisherDTO buildPublisherDTO() {
		return new PublisherDTO(this.id, this.name, this.code, this.foundationDate);
	}

}
