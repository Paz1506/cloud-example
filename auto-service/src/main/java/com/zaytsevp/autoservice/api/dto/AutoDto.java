package com.zaytsevp.autoservice.api.dto;

import com.zaytsevp.autoservice.model.AutoType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Set;
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
public class AutoDto {
    @ApiModelProperty("Идентификатор")
    private UUID id;

    @ApiModelProperty("Наименование")
    private String name;

    @ApiModelProperty("Дата основания")
    private int foundYear;

    @ApiModelProperty("Производимые типы")
    private Set<AutoType> types;
}
