package com.buenoezandro.bookstore.author.builder;

import com.buenoezandro.bookstore.author.dto.AuthorDTO;

import lombok.Builder;

@Builder
public class AuthorDTOBuilder {

	@Builder.Default
	private final Long id = 1L;

	@Builder.Default
	private final String name = "Ezandro Bueno";

	@Builder.Default
	private final int age = 38;

	public AuthorDTO buildAuthorDTO() {
		return new AuthorDTO(this.id, this.name, this.age);
	}

}
