package com.iot.project.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.project.dto.GenreDto;
import com.iot.project.dto.assembler.GenreDtoAssembler;
import com.iot.project.services.GenreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/genre")
@RequiredArgsConstructor
public class GenreController {
    
    final GenreService service;
    final GenreDtoAssembler assembler;

    @GetMapping
    public ResponseEntity<CollectionModel<GenreDto>> getAllGenres(){
        return new ResponseEntity<>(assembler.toCollectionModel(service.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Integer id){
        return new ResponseEntity<>(assembler.toModel(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("{id}/related")
    public ResponseEntity<CollectionModel<GenreDto>> getRelatedGenresById(@PathVariable Integer id){
        return new ResponseEntity<>(assembler.toCollectionModel(service.findById(id).getRelated()), HttpStatus.OK);
    }
}
