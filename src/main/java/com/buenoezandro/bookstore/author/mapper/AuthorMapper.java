package com.buenoezandro.bookstore.author.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.buenoezandro.bookstore.author.dto.AuthorDTO;
import com.buenoezandro.bookstore.author.entity.Author;

@Mapper
public interface AuthorMapper {

	AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

	Author toModel(AuthorDTO authorDTO);

	AuthorDTO toDTO(Author author);

}
