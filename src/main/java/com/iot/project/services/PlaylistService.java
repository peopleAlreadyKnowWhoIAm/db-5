package com.iot.project.services;

import java.util.List;

import com.iot.project.domain.Playlist;

public interface PlaylistService extends GeneralCrudService<Playlist> {
    List<Playlist> findByUserId(Integer userId);

    List<Playlist> findByNameStartingWith(String name);

}