package com.iot.project.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iot.project.domain.User;
import com.iot.project.exceptions.ResourceNotFoundException;
import com.iot.project.repository.UserRepository;
import com.iot.project.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplUserService implements UserService{
    
    final UserRepository rep;

    @Override
    public List<User> findAll() {
        return rep.findAll();
    }

    @Override
    public User findById(Integer id) throws ResourceNotFoundException {
        return rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    @Override
    public User create(User entity) {
        return rep.save(entity);
    }

    @Override
    public void update(Integer id, User entity) throws ResourceNotFoundException {
        User res = rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
        res.setName(entity.getName());
        res.setEmail(entity.getEmail());
        res.setPlaylists(entity.getPlaylists());
        res.setPreferedGenres(entity.getPreferedGenres());
        res.setSavedAlbums(entity.getSavedAlbums());
        res.setSavedSongs(entity.getSavedSongs());
        rep.save(res);
    }

    @Override
    public void delete(Integer id) {
        rep.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return rep.findByEmail(email);
    }

    @Override
    public List<User> findByNameStartingWith(String name) {
        return rep.findByNameStartingWith(name);
    }
    
}
