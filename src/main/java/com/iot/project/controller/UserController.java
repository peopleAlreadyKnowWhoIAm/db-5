package com.iot.project.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.project.dto.AlbumDto;
import com.iot.project.dto.GenreDto;
import com.iot.project.dto.PlaylistDto;
import com.iot.project.dto.SongDto;
import com.iot.project.dto.UserDto;
import com.iot.project.dto.assembler.AlbumDtoAssembler;
import com.iot.project.dto.assembler.GenreDtoAssembler;
import com.iot.project.dto.assembler.PlaylistDtoAssembler;
import com.iot.project.dto.assembler.SongDtoAssembler;
import com.iot.project.dto.assembler.UserDtoAssembler;
import com.iot.project.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    final UserService service;

    final UserDtoAssembler assembler;
    final SongDtoAssembler songAssembler;
    final AlbumDtoAssembler albumAssembler;
    final PlaylistDtoAssembler playlistAssembler;
    final GenreDtoAssembler genreAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<UserDto>> getAllUsers() {

        return new ResponseEntity<>(assembler.toCollectionModel(service.findAll()), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(assembler.toModel(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/song")
    public ResponseEntity<CollectionModel<SongDto>> getSavedSongsById(@PathVariable Integer id){
        return new ResponseEntity<>(songAssembler.toCollectionModel(service.findById(id).getSavedSongs()), HttpStatus.OK);
    }
    @GetMapping("/{id}/album")
    public ResponseEntity<CollectionModel<AlbumDto>> getSavedAlbumsById(@PathVariable Integer id){
        return new ResponseEntity<>(albumAssembler.toCollectionModel(service.findById(id).getSavedAlbums()), HttpStatus.OK);
    }
    @GetMapping("/{id}/genre")
    public ResponseEntity<CollectionModel<GenreDto>> getPreferedGenresById(@PathVariable Integer id){
        return new ResponseEntity<>(genreAssembler.toCollectionModel(service.findById(id).getPreferedGenres()), HttpStatus.OK);
    }
    @GetMapping("/{id}/playlist")
    public ResponseEntity<CollectionModel<PlaylistDto>> getPlaylistsById(@PathVariable Integer id){
        return new ResponseEntity<>(playlistAssembler.toCollectionModel(service.findById(id).getPlaylists()), HttpStatus.OK);
    }
}
