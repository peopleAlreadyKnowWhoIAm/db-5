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

import com.iot.project.domain.Song;
import com.iot.project.dto.AlbumDto;
import com.iot.project.dto.SongDto;
import com.iot.project.dto.assembler.AlbumDtoAssembler;
import com.iot.project.dto.assembler.SongDtoAssembler;
import com.iot.project.services.SongService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/songs")
@RequiredArgsConstructor
public class SongController {

    final SongService service;
    final SongDtoAssembler assembler;
    final AlbumDtoAssembler albumAssembler;


    @GetMapping
    public ResponseEntity<CollectionModel<SongDto>> getAllPlaylists(

            @RequestParam(required = false, name = "search") String search) {
        List<Song> out;
        if (search == null) {

            out = service.findAll();
        } else {
            out = service.findByNameStartingWith(search);
        }
        return new ResponseEntity<>(assembler.toCollectionModel(out),
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
    
    @PostMapping
    public ResponseEntity<SongDto> createAlbum(@RequestBody Song entity){
        Song ret = service.create(entity);
        return new ResponseEntity<>(assembler.toModel(ret), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAlbum(@PathVariable Integer id, @RequestBody Song entity){
        service.update(id, entity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/max-len")
    public ResponseEntity<String> getMaxLen(){
        return new ResponseEntity<>(service.getTheMaxLength(), HttpStatus.OK);
    }
}
