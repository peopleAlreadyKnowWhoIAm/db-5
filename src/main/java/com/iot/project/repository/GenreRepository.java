package com.iot.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.iot.project.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    
   List<Genre> findByNameStartingWith(String name); 

   @Procedure("fill_related_table")
   void fillRelatedTable();
}