package com.iot.project.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iot.project.domain.Playlist;
import com.iot.project.exceptions.ResourceNotFoundException;
import com.iot.project.repository.PlaylistRepository;
import com.iot.project.services.PlaylistService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplPlaylistService implements PlaylistService {

    final PlaylistRepository rep;

    @Override
    public List<Playlist> findAll() {
        return rep.findAll();
    }

    @Override
    public Playlist findById(Integer id) throws ResourceNotFoundException {
        return rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Playlist", id));
    }

    @Override
    public Playlist create(Playlist entity) {
        return rep.save(entity);
    }

    @Override
    public void update(Integer id, Playlist entity) throws ResourceNotFoundException {
        Playlist res = rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Playlist", id));
        res.setName(entity.getName());
        res.setUser(entity.getUser());
        res.setSongs(entity.getSongs());
        rep.save(res);
    }

    @Override
    public void delete(Integer id) {
        rep.deleteById(id);
    }

    @Override
    public List<Playlist> findByUserId(Integer userId) {
        return rep.findByUserId(userId);
    }

    @Override
    public List<Playlist> findByNameStartingWith(String name) {
        return rep.findByNameStartingWith(name);
    }

}
