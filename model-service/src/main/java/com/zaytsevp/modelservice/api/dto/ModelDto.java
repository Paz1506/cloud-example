package com.zaytsevp.modelservice.api.dto;

import lombok.*;

import java.util.UUID;

/**
 * ДТО для отображения модели + названия марки автомобиля (auto-service)
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelDto {

    // ID Записи в БД
    private UUID id;

    // Наименование модели
    private String modelName;

    // Наименование марки модели
    private String autoName;

    // Дата выпуска модели автомобиля
    private int buildYear;
}
