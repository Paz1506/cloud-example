package com.zaytsevp.modelservice.api;

import com.zaytsevp.modelservice.api.dto.AutoDto;
import com.zaytsevp.modelservice.api.dto.ModelDto;
import com.zaytsevp.modelservice.api.dto.ModelProjectionDto;
import com.zaytsevp.modelservice.api.mapper.AutoModelMapper;
import com.zaytsevp.modelservice.feign.AutoServiceFeign;
import com.zaytsevp.modelservice.model.Auto;
import com.zaytsevp.modelservice.model.Model;
import com.zaytsevp.modelservice.model.ModelProjection;
import com.zaytsevp.modelservice.service.ModelService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Pavel Zaytsev
 */
@RestController
@RequestMapping(value = "/models")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ModelController {

    private final ModelService modelService;
    private final AutoServiceFeign autoServiceFeign;
    private final AutoModelMapper autoModelMapper;

    @GetMapping(value = "/all")
    @ApiOperation("Получить все модели")
    public List<ModelDto> getAll() {
        return modelService.getAll().stream()
                           .map(autoModelMapper::toDto)
                           .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Получить модель по идентфикатору")
    public ModelDto getById(@PathVariable("id") UUID id) {
        return autoModelMapper.toDto(modelService.getById(id));
    }

    /** Доступ к данному API имеет только админ */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/dto/{id}")
    @ApiOperation("Получить ДТО проекцию автомобиля по идентификатору")
    public ModelProjectionDto getDtoById(@PathVariable("id") UUID id) {
        Model model = modelService.getById(id);
        Auto auto = autoServiceFeign.getById(model.getAutoId());

        return autoModelMapper.toProjectionDto(ModelProjection.builder()
                                                              .name(model.getName())
                                                              .id(model.getId())
                                                              .auto(auto)
                                                              .buildYear(model.getBuildYear())
                                                              .build());
    }

    @GetMapping(value = "/autos")
    @ApiOperation("Получить все ДТО")
    public List<AutoDto> getDtoAll() {
        return autoServiceFeign.getAll()
                               .stream()
                               .map(autoModelMapper::toAutoDto)
                               .collect(Collectors.toList());
    }

}
