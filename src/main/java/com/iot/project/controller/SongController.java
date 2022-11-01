package com.iot.project.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.project.dto.AlbumDto;
import com.iot.project.dto.SongDto;
import com.iot.project.dto.assembler.AlbumDtoAssembler;
import com.iot.project.dto.assembler.SongDtoAssembler;
import com.iot.project.services.SongService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/song")
@RequiredArgsConstructor
public class SongController {

    final SongService service;
    final SongDtoAssembler assembler;
    final AlbumDtoAssembler albumAssembler;


    @GetMapping
    public ResponseEntity<CollectionModel<SongDto>> getAllPlaylists() {
        return new ResponseEntity<>(assembler.toCollectionModel(service.findAll()),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SongDto> getSongById(@PathVariable Integer id) {
        return new ResponseEntity<>(assembler.toModel(service.findById(id)), HttpStatus.OK);
    }
    
    @GetMapping("{id}/album")
    public ResponseEntity<AlbumDto> getAlbumBySong(@PathVariable Integer id) {
        return new ResponseEntity<>(albumAssembler.toModel(service.findById(id).getAlbum()), HttpStatus.OK);
    } 
}
