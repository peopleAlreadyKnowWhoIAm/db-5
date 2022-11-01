package com.iot.project.dto;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "playlist", collectionRelation = "playlists")
public class SongDto extends RepresentationModel<SongDto>{
    private final Integer id;
    private final String name;
    private final Integer length;
    private final CollectionModel<AuthorDto> authors;
    private final GenreDto genre;
}
