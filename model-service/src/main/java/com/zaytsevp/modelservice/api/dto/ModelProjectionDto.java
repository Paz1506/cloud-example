package com.zaytsevp.modelservice.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.UUID;

/**
 * @author Pavel Zaytsev
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Модель")
public class ModelProjectionDto {

    @ApiModelProperty("Идентификатор")
    private UUID id;

    @ApiModelProperty("Наименование")
    private String name;

    @ApiModelProperty("Авто")
    private AutoDto auto;

    @ApiModelProperty("Дата выпуска модели")
    private int buildYear;
}
