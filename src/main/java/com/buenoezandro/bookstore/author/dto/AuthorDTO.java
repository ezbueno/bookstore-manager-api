package com.buenoezandro.bookstore.author.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

	private Long id;

	@NotEmpty
	@Size(max = 255)
	private String name;

	@NotNull
	@Max(value = 120)
	private int age;

}
