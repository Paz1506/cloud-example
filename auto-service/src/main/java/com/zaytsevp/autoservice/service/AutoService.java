package com.zaytsevp.autoservice.service;

import com.zaytsevp.autoservice.model.Auto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Сервис для работы с сущностью "Авто"
 *
 * @author Pavel Zaytsev
 */
public interface AutoService {

    List<Auto> getAll();

    Auto getById(UUID id);

    Auto createRandom();

}
