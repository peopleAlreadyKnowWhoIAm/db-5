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

import com.iot.project.domain.Label;
import com.iot.project.dto.AlbumDto;
import com.iot.project.dto.LabelDto;
import com.iot.project.dto.assembler.AlbumDtoAssembler;
import com.iot.project.dto.assembler.LabelDtoAssembler;
import com.iot.project.services.LabelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/labels")
@RequiredArgsConstructor
public class LabelController {

    final LabelService service;

    final AlbumDtoAssembler albumAssembler;
    final LabelDtoAssembler assembler;

    @GetMapping
    public ResponseEntity<CollectionModel<LabelDto>> getAllLabels(

            @RequestParam(required = false, name = "search") String search) {
        List<Label> out;
        if (search == null) {

            out = service.findAll();
        } else {
            out = service.findByNameStartingWith(search);
        }
        return new ResponseEntity<CollectionModel<LabelDto>>(assembler.toCollectionModel(out),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabelDto>getLabelById(@PathVariable Integer id){
        return new ResponseEntity<LabelDto>(assembler.toModel(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("{id}/albums")
    public ResponseEntity<CollectionModel<AlbumDto>> getAllAlbumsByLabel(@PathVariable Integer id){
        return new ResponseEntity<>(albumAssembler.toCollectionModel(service.findById(id).getAlbums()), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<LabelDto> createAlbum(@RequestBody Label label){
        Label ret = service.create(label);
        return new ResponseEntity<>(assembler.toModel(ret), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAlbum(@PathVariable Integer id, @RequestBody Label entity){
        service.update(id, entity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
