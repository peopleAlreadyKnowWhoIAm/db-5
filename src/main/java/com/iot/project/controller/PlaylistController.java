package com.iot.project.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iot.project.domain.Playlist;
import com.iot.project.dto.PlaylistDto;
import com.iot.project.dto.SongDto;
import com.iot.project.dto.UserDto;
import com.iot.project.dto.assembler.PlaylistDtoAssembler;
import com.iot.project.dto.assembler.SongDtoAssembler;
import com.iot.project.dto.assembler.UserDtoAssembler;
import com.iot.project.services.PlaylistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/playlists")
public class PlaylistController {

    final PlaylistService service;
    final PlaylistDtoAssembler assembler;
    final SongDtoAssembler songAssembler;
    final UserDtoAssembler userAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<PlaylistDto>> getAllPlaylists(
            @RequestParam(required = false, name = "search") String search) {
        List<Playlist> out;
        if (search == null) {

            out = service.findAll();
        } else {
            out = service.findByNameStartingWith(search);
        }
        return new ResponseEntity<CollectionModel<PlaylistDto>>(assembler.toCollectionModel(out),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PlaylistDto> getPlaylistById(@PathVariable Integer id) {
        return new ResponseEntity<PlaylistDto>(assembler.toModel(service.findById(id)), HttpStatus.OK);
    }
    
    @GetMapping("{id}/song")
    public ResponseEntity<CollectionModel<SongDto>> getPlaylistSongsById(@PathVariable Integer id) {
        return new ResponseEntity<>(songAssembler.toCollectionModel(service.findById(id).getSongs()), HttpStatus.OK);
    }
    @GetMapping("{id}/user")
    public ResponseEntity<UserDto> getUserByPlaylist(@PathVariable Integer id) {
        return new ResponseEntity<>(userAssembler.toModel(service.findById(id).getUser()), HttpStatus.OK);
    } 

    @PostMapping
    public ResponseEntity<PlaylistDto> createAlbum(@RequestBody Playlist entity){
        Playlist ret = service.create(entity);
        return new ResponseEntity<>(assembler.toModel(ret), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAlbum(@PathVariable Integer id, @RequestBody Playlist entity){
        service.update(id, entity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
