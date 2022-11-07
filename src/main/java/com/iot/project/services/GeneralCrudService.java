package com.iot.project.services;

import java.util.List;

import com.iot.project.exceptions.ResourceNotFoundException;

public interface GeneralCrudService<T> {
        List<T> findAll();

    T findById(Integer id) throws ResourceNotFoundException;

    T create(T entity);
    
    void update(Integer id, T entity) throws ResourceNotFoundException;

    void delete(Integer id);
}
