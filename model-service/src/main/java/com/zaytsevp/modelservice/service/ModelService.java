package com.zaytsevp.modelservice.service;

import com.zaytsevp.modelservice.model.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Сервис для работы с сущностью "Модель"
 *
 * @author Pavel Zaytsev
 */
public interface ModelService {

    /** получить все модели */
    List<Model> getAll();

    /** получить модель по идентификатору */
    Optional<Model> getById(UUID id);

}
