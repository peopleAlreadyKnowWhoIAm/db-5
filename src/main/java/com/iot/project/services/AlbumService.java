package com.iot.project.services;

import java.util.List;

import com.iot.project.domain.Album;

public interface AlbumService extends GeneralCrudService<Album> {
    List<Album> findByLabelId(Integer labelId);

    List<Album> findByNameStartingWith(String name);

}
