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

import com.iot.project.domain.Album;
import com.iot.project.dto.AlbumDto;
import com.iot.project.dto.SongDto;
import com.iot.project.dto.assembler.AlbumDtoAssembler;
import com.iot.project.dto.assembler.SongDtoAssembler;
import com.iot.project.services.AlbumService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/albums")
@RequiredArgsConstructor
public class AlbumController {

    final AlbumService service;
    final AlbumDtoAssembler assembler;
    final SongDtoAssembler songAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<AlbumDto>> getAllAlbums(
            @RequestParam(required = false, name = "search") String search) {
        List<Album> out;
        if (search == null) {

            out = service.findAll();
        } else {
            out = service.findByNameStartingWith(search);
        }

        return new ResponseEntity<>(assembler.toCollectionModel(out), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> getAlbumById(@PathVariable Integer id) {
        return new ResponseEntity<>(assembler.toModel(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<CollectionModel<SongDto>> getAllSongsFromAlbum(@PathVariable Integer id) {
        return new ResponseEntity<CollectionModel<SongDto>>(
                songAssembler.toCollectionModel(service.findById(id).getSongs()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlbumDto> createAlbum(@RequestBody Album album) {
        Album ret = service.create(album);
        return new ResponseEntity<>(assembler.toModel(ret), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAlbum(@PathVariable Integer id, @RequestBody Album entity) {
        service.update(id, entity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
