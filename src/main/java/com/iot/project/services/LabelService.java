package com.iot.project.services;

import java.util.List;

import com.iot.project.domain.Label;

public interface LabelService extends GeneralCrudService<Label> {

    List<Label> findByNameStartingWith(String name);
}
