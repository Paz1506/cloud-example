package com.zaytsevp.modelservice.model;

import lombok.*;

/**
 * Класс "Автомобиль"
 *
 * @author Pavel Zaytsev
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auto extends BaseEntity {

    private String name;

    private int foundYear;

}
