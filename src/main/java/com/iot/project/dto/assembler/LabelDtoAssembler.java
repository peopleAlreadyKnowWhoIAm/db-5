package com.iot.project.dto.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.iot.project.controller.LabelController;
import com.iot.project.domain.Label;
import com.iot.project.dto.LabelDto;

@Component
public class LabelDtoAssembler implements RepresentationModelAssembler<Label, LabelDto> {

    @Override
    public LabelDto toModel(Label entity) {
        LabelDto out = LabelDto.builder().id(entity.getId()).name(entity.getName()).build();

        Link selfLink = linkTo(methodOn(LabelController.class).getLabelById(out.getId())).withSelfRel();
        Link albumsLink = linkTo(methodOn(LabelController.class).getAllAlbumsByLabel(out.getId())).withRel("albums");
        out.add(selfLink, albumsLink);
        return out;
    }

}
