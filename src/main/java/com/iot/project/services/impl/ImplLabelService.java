package com.iot.project.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iot.project.domain.Label;
import com.iot.project.exceptions.ResourceNotFoundException;
import com.iot.project.repository.LabelRepository;
import com.iot.project.services.LabelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImplLabelService implements LabelService {

    final LabelRepository rep;

    @Override
    public List<Label> findAll() {
        return rep.findAll();
    }

    @Override
    public Label findById(Integer id) throws ResourceNotFoundException {
        return rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Label", id));
    }

    @Override
    public Label create(Label entity) {
        return rep.save(entity);
    }

    @Override
    public void update(Integer id, Label entity) throws ResourceNotFoundException {
        Label res = rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Label", id));
        res.setName(entity.getName());
        rep.save(res);
    }

    @Override
    public void delete(Integer id) {
        rep.deleteById(id);
    }

    @Override
    public List<Label> findByNameStartingWith(String name) {
        return rep.findByNameStartingWith(name);
    }

}
