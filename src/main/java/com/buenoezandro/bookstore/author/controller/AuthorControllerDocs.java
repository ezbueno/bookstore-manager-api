package com.buenoezandro.bookstore.author.controller;

import com.buenoezandro.bookstore.author.dto.AuthorDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Authors management")
public interface AuthorControllerDocs {

	@ApiOperation(value = "Find author by id operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success author found"),
			@ApiResponse(code = 404, message = "Author not found error code") })
	AuthorDTO findById(Long id);

	@ApiOperation(value = "Author creation operation")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Success author creation"),
			@ApiResponse(code = 400, message = "Missing required fields, wrong field range value or author already registered on system") })
	AuthorDTO create(AuthorDTO authorDTO);
}
