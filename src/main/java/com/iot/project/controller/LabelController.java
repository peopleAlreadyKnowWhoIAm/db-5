package com.iot.project.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.project.dto.LabelDto;
import com.iot.project.dto.assembler.LabelDtoAssembler;
import com.iot.project.services.LabelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/label")
@RequiredArgsConstructor
public class LabelController {

    final LabelService service;
    final LabelDtoAssembler assembler;

    @GetMapping
    public ResponseEntity<CollectionModel<LabelDto>> getAllLabels() {
        return new ResponseEntity<CollectionModel<LabelDto>>(assembler.toCollectionModel(service.findAll()),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LabelDto>getLabelById(@PathVariable Integer id){
        return new ResponseEntity<LabelDto>(assembler.toModel(service.findById(id)), HttpStatus.OK);
    }
}
