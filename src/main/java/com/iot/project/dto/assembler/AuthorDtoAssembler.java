package com.iot.project.dto.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.iot.project.controller.AuthorController;
import com.iot.project.domain.Author;
import com.iot.project.dto.AuthorDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AuthorDtoAssembler implements RepresentationModelAssembler<Author, AuthorDto> {

    @Override
    public AuthorDto toModel(Author entity) {
        AuthorDto out = AuthorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(AuthorController.class).getAuthorById(out.getId())).withSelfRel();
        Link songsLink = linkTo(methodOn(AuthorController.class).getAllSongsByAuthorId(out.getId())).withRel("songs");
        Link albumsLink = linkTo(methodOn(AuthorController.class).getAllAlbumsByAuthorId(out.getId())).withRel("albums");

        out.add(selfLink, songsLink, albumsLink);
        return out;
    }

}
