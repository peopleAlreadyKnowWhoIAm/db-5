package com.iot.project.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iot.project.domain.Author;
import com.iot.project.exceptions.ResourceNotFoundException;
import com.iot.project.repository.AuthorRepository;
import com.iot.project.services.AuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplAuthorService implements AuthorService {

    final AuthorRepository rep;

    @Override
    public List<Author> findAll() {
        return rep.findAll();
    }

    @Override
    public Author findById(Integer id) throws ResourceNotFoundException {
       return rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", id));
    }

    @Override
    public Author create(Author entity) {
        return rep.save(entity);
    }

    @Override
    public void update(Integer id, Author entity) throws ResourceNotFoundException {
        Author res = rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", id));
        res.setName(entity.getName());
        rep.save(res);
    }

    @Override
    public void delete(Integer id) {
        rep.deleteById(id);
    }

    @Override
    public List<Author> findByNameStartingWith(String name) {
        return rep.findByNameStartingWith(name);
    }

}
