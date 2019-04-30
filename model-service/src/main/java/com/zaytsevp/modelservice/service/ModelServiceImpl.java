package com.zaytsevp.modelservice.service;

import com.zaytsevp.modelservice.model.Model;
import com.zaytsevp.modelservice.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * @author Pavel Zaytsev
 */
@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Model> getAll() {
        return modelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Model getById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Get by null id");
        }

        return modelRepository.findById(id)
                              .orElseThrow(() -> new EntityNotFoundException("Модель не найдена"));
    }
}
