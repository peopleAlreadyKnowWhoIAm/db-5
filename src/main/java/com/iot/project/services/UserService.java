package com.iot.project.services;

import java.util.List;
import java.util.Optional;

import com.iot.project.domain.User;

public interface UserService extends GeneralCrudService<User> {
    Optional<User> findByEmail(String email);

    List<User> findByNameStartingWith(String name);

}
