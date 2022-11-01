package com.iot.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iot.project.domain.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    
    List<Playlist> findByUserId(Integer userId);
    List<Playlist> findByNameStartingWith(String name);
}