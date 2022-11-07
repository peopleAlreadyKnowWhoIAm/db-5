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

import com.iot.project.domain.Author;
import com.iot.project.dto.AlbumDto;
import com.iot.project.dto.AuthorDto;
import com.iot.project.dto.SongDto;
import com.iot.project.dto.assembler.AlbumDtoAssembler;
import com.iot.project.dto.assembler.AuthorDtoAssembler;
import com.iot.project.dto.assembler.SongDtoAssembler;
import com.iot.project.services.AuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    final AuthorService service;

    final AuthorDtoAssembler assembler;
    final SongDtoAssembler songAssembler;
    final AlbumDtoAssembler albumAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<AuthorDto>> getAllAuthors(

            @RequestParam(required = false, name = "search") String search) {
        List<Author> out;
        if (search == null) {

            out = service.findAll();
        } else {
            out = service.findByNameStartingWith(search);
        }

        return new ResponseEntity<CollectionModel<AuthorDto>>(assembler.toCollectionModel(out), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Integer id) {
        return new ResponseEntity<AuthorDto>(assembler.toModel(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<CollectionModel<SongDto>> getAllSongsByAuthorId(@PathVariable Integer id){
        return new ResponseEntity<>(songAssembler.toCollectionModel(service.findById(id).getSongs()), HttpStatus.OK);
    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<CollectionModel<AlbumDto>> getAllAlbumsByAuthorId(@PathVariable Integer id){
        return new ResponseEntity<>(albumAssembler.toCollectionModel(service.findById(id).getAlbums()), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<AuthorDto> createAlbum(@RequestBody Author album){
        Author ret = service.create(album);
        return new ResponseEntity<>(assembler.toModel(ret), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAlbum(@PathVariable Integer id, @RequestBody Author entity){
        service.update(id, entity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
