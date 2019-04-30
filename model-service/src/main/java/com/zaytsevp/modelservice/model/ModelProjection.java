package com.zaytsevp.modelservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Проекция модели с информацией о производителе
 *
 * @author Pavel Zaytsev
 */
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelProjection {

    private UUID id;

    private Auto auto;

    private String name;

    private int buildYear;
}
