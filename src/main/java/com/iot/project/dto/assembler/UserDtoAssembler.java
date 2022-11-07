package com.iot.project.dto.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.iot.project.controller.UserController;
import com.iot.project.domain.User;
import com.iot.project.dto.UserDto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserDtoAssembler implements RepresentationModelAssembler<User, UserDto> {

    @Override
    public UserDto toModel(User entity) {
        UserDto out = UserDto.builder().id(entity.getId()).email(entity.getEmail()).name(entity.getName()).build();
        Link selfLink = linkTo(methodOn(UserController.class).getUserById(out.getId())).withSelfRel();
        Link savedSongsLink = linkTo(methodOn(UserController.class).getSavedSongsById(out.getId())).withRel("saved_songs");
        Link savedAlbumsLink = linkTo(methodOn(UserController.class).getSavedAlbumsById(out.getId())).withRel("saved_albums");
        Link playlistsLink = linkTo(methodOn(UserController.class).getPlaylistsById(out.getId())).withRel("user_playlists");
        Link preferedGenresLink = linkTo(methodOn(UserController.class).getPreferedGenresById(out.getId())).withRel("prefered_genres");
    
        out.add(selfLink, savedAlbumsLink, savedSongsLink, playlistsLink, preferedGenresLink);

        return out;
    }

}
