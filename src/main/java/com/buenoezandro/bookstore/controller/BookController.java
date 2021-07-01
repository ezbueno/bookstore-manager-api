package com.buenoezandro.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {

	@ApiOperation(value = "Hello, Bookstore Manager!")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success method return") })
	@GetMapping
	public String hello() {
		return "Hello, Bookstore Manager!";
	}

}
