package com.iot.project.services;

import java.util.List;

import com.iot.project.domain.Genre;

public interface GenreService extends GeneralCrudService<Genre> {
    List<Genre> findByNameStartingWith(String name);

}
