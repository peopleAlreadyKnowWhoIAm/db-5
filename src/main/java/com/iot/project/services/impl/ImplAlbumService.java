package com.iot.project.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.project.domain.Album;
import com.iot.project.exceptions.ResourceNotFoundException;
import com.iot.project.repository.AlbumRepository;
import com.iot.project.services.AlbumService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImplAlbumService implements AlbumService {

    final AlbumRepository rep;

    @Override
    public List<Album> findAll() {
        return rep.findAll();
    }

    @Override
    public Album findById(Integer id) throws ResourceNotFoundException {
        return rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album", id));
    }

    @Override
    @Transactional
    public Album create(Album entity) {
        rep.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, Album entity) throws ResourceNotFoundException {
        Album res = rep.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Album", id));
        res.setAuthors(entity.getAuthors());
        res.setLabel(entity.getLabel());
        res.setName(entity.getName());
        res.setYearOfPublishing(entity.getYearOfPublishing());
        res.setAuthors(entity.getAuthors());
        rep.save(res);

    }

    @Override
    public void delete(Integer id) {
        rep.deleteById(id);
    }

    @Override
    public List<Album> findByLabelId(Integer labelId) {
        return rep.findByLabelId(labelId);
    }

    @Override
    public List<Album> findByNameStartingWith(String name) {
        return rep.findByNameStartingWith(name);
    }

}
