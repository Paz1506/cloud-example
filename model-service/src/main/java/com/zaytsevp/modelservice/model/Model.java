package com.zaytsevp.modelservice.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MODEL")
@EqualsAndHashCode(callSuper = true)
public class Model extends BaseEntity {

    @Builder
    public Model(UUID autoId, String name, int buildYear) {
        this.autoId = autoId;
        this.name = name;
        this.buildYear = buildYear;
    }

    /**
     * Идентификатор записи справочника "Модель авто"
     */
    @Type(type = "uuid-char")
    @Column(name = "AUTO_ID", nullable = false, length = 36)
    private UUID autoId;

    /**
     * Наименование модели автомобиля
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * Дата основания
     */
    @Column(name = "BUILD_YEAR", nullable = false)
    private int buildYear;

}
