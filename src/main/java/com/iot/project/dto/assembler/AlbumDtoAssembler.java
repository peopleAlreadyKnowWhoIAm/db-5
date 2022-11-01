package com.iot.project.dto.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.iot.project.controller.AlbumController;
import com.iot.project.domain.Album;
import com.iot.project.dto.AlbumDto;

import lombok.RequiredArgsConstructor;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
@RequiredArgsConstructor
public class AlbumDtoAssembler implements RepresentationModelAssembler<Album, AlbumDto>{

    private final AuthorDtoAssembler authorDtoAssembler;
    private final LabelDtoAssembler labelDtoAssembler;

    @Override
    public AlbumDto toModel(Album entity) {
        AlbumDto out = AlbumDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .yearOfPublishing(entity.getYearOfPublishing())
                .authors(authorDtoAssembler.toCollectionModel(entity.getAuthors()))
                .label(labelDtoAssembler.toModel(entity.getLabel()))
                .build();

        Link selfLink = linkTo(methodOn(AlbumController.class).getAlbumById(out.getId())).withSelfRel();
        Link songsLink  = linkTo(methodOn(AlbumController.class).getAllSongsFromAlbum(out.getId())).withRel("songs");

        out.add(selfLink, songsLink);
        
        return out;
    }
    
}
