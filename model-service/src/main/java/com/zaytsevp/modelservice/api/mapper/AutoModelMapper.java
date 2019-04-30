package com.zaytsevp.modelservice.api.mapper;

import com.zaytsevp.modelservice.api.dto.AutoDto;
import com.zaytsevp.modelservice.api.dto.ModelDto;
import com.zaytsevp.modelservice.api.dto.ModelProjectionDto;
import com.zaytsevp.modelservice.model.Auto;
import com.zaytsevp.modelservice.model.Model;
import com.zaytsevp.modelservice.model.ModelProjection;
import org.mapstruct.Mapper;

/**
 * @author Pavel Zaytsev
 */
@Mapper
public interface AutoModelMapper {
    ModelProjectionDto toProjectionDto(ModelProjection projection);

    ModelDto toDto(Model model);

    AutoDto toAutoDto(Auto auto);
}
