package com.iot.project.dto.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.iot.project.controller.PlaylistController;
import com.iot.project.domain.Playlist;
import com.iot.project.dto.PlaylistDto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PlaylistDtoAssembler implements RepresentationModelAssembler<Playlist, PlaylistDto> {

    @Override
    public PlaylistDto toModel(Playlist entity) {
        PlaylistDto out = PlaylistDto.builder().id(entity.getId()).name(entity.getName()).build();
        Link selfLink = linkTo(methodOn(PlaylistController.class).getPlaylistById(out.getId())).withSelfRel();
        Link songsLink = linkTo(methodOn(PlaylistController.class).getPlaylistSongsById(out.getId())).withRel("songs");
        Link userLink =linkTo(methodOn(PlaylistController.class).getUserByPlaylist(out.getId())).withRel("user");

        out.add(selfLink, songsLink, userLink);
        return out;
    }

}
