package com.iot.project.dto.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.iot.project.controller.SongController;
import com.iot.project.domain.Song;
import com.iot.project.dto.SongDto;

import lombok.RequiredArgsConstructor;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
@RequiredArgsConstructor
public class SongDtoAssembler implements RepresentationModelAssembler<Song, SongDto> {
    
    private final AuthorDtoAssembler authorDtoAssembler;
    private final GenreDtoAssembler genreDtoAssembler;

    @Override
    public SongDto toModel(Song entity) {
        SongDto out = SongDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .length(entity.getLength())
                .authors(authorDtoAssembler.toCollectionModel(entity.getAuthors()))
                .genre(genreDtoAssembler.toModel(entity.getGenre()))
                .build();

        Link selfLink = linkTo(methodOn(SongController.class).getSongById(out.getId())).withSelfRel();
        Link albumLink = linkTo(methodOn(SongController.class).getAlbumBySong(out.getId())).withRel("album");

        out.add(selfLink, albumLink);
        
        return out;
    }
}
