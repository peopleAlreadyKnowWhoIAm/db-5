package com.iot.project.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iot.project.domain.Song;
import com.iot.project.exceptions.ResourceNotFoundException;
import com.iot.project.repository.SongRepository;
import com.iot.project.services.SongService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplSongService implements SongService {
    
    final SongRepository rep;
    
    @Override
    public List<Song> findAll() {
        return rep.findAll();
    }

    @Override
    public Song findById(Integer id) throws ResourceNotFoundException {
        return rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song", id));
    }

    @Override
    public Song create(Song entity) {
        return rep.save(entity);
    }

    @Override
    public void update(Integer id, Song entity) throws ResourceNotFoundException {
        Song res = rep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song", id));
        res.setName(entity.getName());
        res.setAlbum(entity.getAlbum());
        res.setAuthors(entity.getAuthors());
        res.setGenre(entity.getGenre());
        res.setLength(entity.getLength());
        rep.save(res);
    }

    @Override
    public void delete(Integer id) {
        rep.deleteById(id);
    }

    @Override
    public List<Song> findByNameStartingWith(String name) {
        return rep.findByNameStartingWith(name);
    }

    @Override
    public List<Song> findByGenreId(Integer genreId) {
        return rep.findByGenreId(genreId);
    }
    
}
