package com.iot.project.services;

import java.util.List;

import com.iot.project.domain.Song;

public interface SongService extends GeneralCrudService<Song> {
   List<Song> findByNameStartingWith(String name);

   List<Song> findByGenreId(Integer genreId);
}
