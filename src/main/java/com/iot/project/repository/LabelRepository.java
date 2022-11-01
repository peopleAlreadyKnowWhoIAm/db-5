package com.iot.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iot.project.domain.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {
   List<Label> findByNameStartingWith(String name); 
}