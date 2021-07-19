package com.buenoezandro.bookstore.publisher.controller;

import com.buenoezandro.bookstore.publisher.dto.PublisherDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Publishers management")
public interface PublisherControllerDocs {

	@ApiOperation(value = "Publisher creation operation")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Success publisher creation"),
			@ApiResponse(code = 400, message = "Missing required fields, wrong field range value or publisher already registered on system") })
	PublisherDTO create(PublisherDTO publisherDTO);

}
