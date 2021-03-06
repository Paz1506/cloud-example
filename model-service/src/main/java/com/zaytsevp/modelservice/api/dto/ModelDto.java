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
@ApiModel("Авто")
public class ModelDto {

    @ApiModelProperty("Идентификатор")
    private UUID id;

    @ApiModelProperty("Наименование")
    private String name;

    @ApiModelProperty("Идентификатор автопроизводителя")
    private UUID autoId;

    @ApiModelProperty("Год основания")
    private int foundYear;
}
