package com.iot.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iot.project.domain.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByLabelId(Integer labelId);

    List<Album> findByNameStartingWith(String name);
}