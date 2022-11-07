package com.iot.project.dto.assembler;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.iot.project.controller.GenreController;
import com.iot.project.domain.Genre;
import com.iot.project.dto.GenreDto;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Link;

@Component
public class GenreDtoAssembler implements RepresentationModelAssembler<Genre, GenreDto> {

    @Override
    public GenreDto toModel(Genre entity) {
        GenreDto out = GenreDto.builder().id(entity.getId()).name(entity.getName()).build();

        Link selfLink = linkTo(methodOn(GenreController.class).getGenreById(out.getId())).withSelfRel(); 
        Link relatedLink = linkTo(methodOn(GenreController.class).getRelatedGenresById(out.getId())).withRel("related");

        out.add(selfLink, relatedLink);
        return out;
    }
    
}
