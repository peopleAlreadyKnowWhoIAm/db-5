package com.iot.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iot.project.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
   List<Author> findByNameStartingWith(String name); 
}