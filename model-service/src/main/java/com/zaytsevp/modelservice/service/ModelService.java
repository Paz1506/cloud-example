package com.zaytsevp.modelservice.service;

import com.zaytsevp.modelservice.model.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModelService {

    List<Model> getAll();

    Optional<Model> getById(UUID id);

}
