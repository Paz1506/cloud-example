package com.zaytsevp.autoservice.api.mapper;

import com.zaytsevp.autoservice.api.dto.AutoDto;
import com.zaytsevp.autoservice.model.Auto;
import org.mapstruct.Mapper;

/**
 * @author Pavel Zaytsev
 */
@Mapper
public interface AutoMapper {
    AutoDto toDto(Auto auto);
}
