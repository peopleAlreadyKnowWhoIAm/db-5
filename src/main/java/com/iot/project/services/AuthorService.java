package com.iot.project.services;

import java.util.List;

import com.iot.project.domain.Author;

public interface AuthorService extends GeneralCrudService<Author> {
    List<Author> findByNameStartingWith(String name);

}
