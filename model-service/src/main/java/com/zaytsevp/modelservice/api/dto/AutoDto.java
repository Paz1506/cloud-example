package com.zaytsevp.modelservice.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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

    @ApiModelProperty("Наименование")
    private String name;

    @ApiModelProperty("Год основания")
    private int foundYear;
}
