package com.zaytsevp.modelservice.service;

import com.zaytsevp.modelservice.model.Model;
import com.zaytsevp.modelservice.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Model> getAll() {
        return modelRepository.findAll();
    }

    @Override
    public Optional<Model> getById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Get by null id");
        }

        return modelRepository.findById(id);
    }
}
