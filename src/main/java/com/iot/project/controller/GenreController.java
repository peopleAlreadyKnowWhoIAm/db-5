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

import com.iot.project.domain.Genre;
import com.iot.project.dto.GenreDto;
import com.iot.project.dto.assembler.GenreDtoAssembler;
import com.iot.project.services.GenreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/genres")
@RequiredArgsConstructor
public class GenreController {

    final GenreService service;
    final GenreDtoAssembler assembler;

    @GetMapping
    public ResponseEntity<CollectionModel<GenreDto>> getAllGenres(

            @RequestParam(required = false, name = "search") String search) {
        List<Genre> out;
        if (search == null) {

            out = service.findAll();
        } else {
            out = service.findByNameStartingWith(search);
        }
        return new ResponseEntity<>(assembler.toCollectionModel(out), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Integer id) {
        return new ResponseEntity<>(assembler.toModel(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("{id}/related")
    public ResponseEntity<CollectionModel<GenreDto>> getRelatedGenresById(@PathVariable Integer id) {
        return new ResponseEntity<>(assembler.toCollectionModel(service.findById(id).getRelated()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenreDto> createAlbum(@RequestBody Genre album) {
        Genre ret = service.create(album);
        return new ResponseEntity<>(assembler.toModel(ret), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAlbum(@PathVariable Integer id, @RequestBody Genre entity) {
        service.update(id, entity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/fill-related")
    public ResponseEntity<Void> fillRelatedtable(){
        service.fillRelatedTable();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
