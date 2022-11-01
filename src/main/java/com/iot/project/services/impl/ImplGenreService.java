package com.iot.project.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iot.project.domain.Genre;
import com.iot.project.exceptions.ResourceNotFoundException;
import com.iot.project.repository.GenreRepository;
import com.iot.project.services.GenreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplGenreService implements GenreService {

    final GenreRepository rep;

    @Override
    public List<Genre> findAll() {
        return rep.findAll();
    }

    @Override
    public Genre findById(Integer id) throws ResourceNotFoundException {
        return rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre", id));
    }

    @Override
    public Genre create(Genre entity) {
        return rep.save(entity);
    }

    @Override
    public void update(Integer id, Genre entity) throws ResourceNotFoundException {

        Genre res = rep.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Genre", id));
        res.setName(entity.getName());
        res.setRelated(entity.getRelated());
        rep.save(res);
    }

    @Override
    public void delete(Integer id) {
        rep.deleteById(id);
    }

    @Override
    public List<Genre> findByNameStartingWith(String name) {
        return rep.findByNameStartingWith(name);
    }

}
