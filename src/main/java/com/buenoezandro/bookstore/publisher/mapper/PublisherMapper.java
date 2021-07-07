package com.buenoezandro.bookstore.publisher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.buenoezandro.bookstore.publisher.dto.PublisherDTO;
import com.buenoezandro.bookstore.publisher.entity.Publisher;

@Mapper
public interface PublisherMapper {

	PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

	@Mapping(target = "createdDate", ignore = true)
	@Mapping(target = "lastModifiedDate", ignore = true)
	@Mapping(target = "books", ignore = true)
	Publisher toModel(PublisherDTO publisherDTO);

	PublisherDTO toDTO(Publisher publisher);

}
