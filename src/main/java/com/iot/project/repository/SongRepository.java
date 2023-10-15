package com.iot.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.iot.project.domain.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
   List<Song> findByNameStartingWith(String name);

   List<Song> findByGenreId(Integer genreId);

   @Procedure("show_max_length_for_song")
   String getTheMaxLength();
}