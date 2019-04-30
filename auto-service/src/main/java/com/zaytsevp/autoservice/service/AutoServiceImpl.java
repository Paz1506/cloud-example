package com.zaytsevp.autoservice.service;

import com.google.common.collect.Sets;
import com.zaytsevp.autoservice.model.Auto;
import com.zaytsevp.autoservice.model.AutoType;
import com.zaytsevp.autoservice.repository.AutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author Pavel Zaytsev
 */
@Service
@RequiredArgsConstructor
public class AutoServiceImpl implements AutoService {

    private final AutoRepository autoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Auto> getAll() {
        return autoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Auto getById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Не передан идентификатор авто");
        }
        return autoRepository.findById(id)
                             .orElseThrow(() -> new EntityNotFoundException("Авто не найден"));
    }

    @Override
    @Transactional
    public Auto createRandom() {
        int foundYear = new Random().nextInt(2000);

        Auto auto = Auto.builder()
                        .foundYear(foundYear)
                        .name("Random auto " + foundYear)
                        .types(Sets.newHashSet(AutoType.LIGHT))
                        .build();

        return autoRepository.saveAndFlush(auto);
    }
}
