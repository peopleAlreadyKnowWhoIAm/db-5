package com.iot.project.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.project.domain.Author;
import com.iot.project.dto.AuthorDto;
import com.iot.project.dto.SongDto;
import com.iot.project.dto.assembler.AuthorDtoAssembler;
import com.iot.project.services.AuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorController {

    final AuthorService service;

    final AuthorDtoAssembler assembler;

    @GetMapping
    public ResponseEntity<CollectionModel<AuthorDto>> getAllAuthors(){
        List<Author> out = service.findAll();

        return new ResponseEntity<CollectionModel<AuthorDto>>(assembler.toCollectionModel(out), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Integer id) {
        return new ResponseEntity<AuthorDto>(assembler.toModel(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/song")
    public ResponseEntity<List<SongDto>> getAllSongsByAuthorId(@PathVariable Integer id){
        return null;
    }

    @GetMapping("/{id}/album")
    public ResponseEntity<List<SongDto>> getAllAlbumsByAuthorId(@PathVariable Integer id){
        return null;
    }
}
