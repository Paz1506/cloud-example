package com.zaytsevp.modelservice.api;

import com.zaytsevp.modelservice.api.dto.ModelDto;
import com.zaytsevp.modelservice.feign.AutoServiceFeign;
import com.zaytsevp.modelservice.model.Auto;
import com.zaytsevp.modelservice.model.Model;
import com.zaytsevp.modelservice.service.ModelService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * @author Pavel Zaytsev
 */
@RestController
@RequestMapping(value = "/models")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ModelController {

    private final ModelService modelService;
    private final AutoServiceFeign autoServiceFeign;

    @GetMapping(value = "/all")
    @ApiOperation("Получить все модели")
    public List<Model> getAll() {
        return modelService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Получить модель по идентфикатору")
    public Model getById(@PathVariable("id") UUID id) {

        return modelService.getById(id).orElseThrow(EntityNotFoundException::new);
    }

    /** Доступ к данному API имеет только админ */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/dto/{id}")
    @ApiOperation("Получить ДТО автомобиля по идентификатору")
    public ModelDto getDtoById(@PathVariable("id") UUID id) {
        Model model = modelService.getById(id).orElseThrow(EntityNotFoundException::new);
        Auto auto = autoServiceFeign.getById(model.getAutoId());

        return ModelDto.builder()
                       .modelName(model.getName())
                       .id(auto.getId())
                       .autoName(auto.getName())
                       .buildYear(model.getBuildYear())
                       .build();
    }

    @GetMapping(value = "/autos")
    @ApiOperation("Получить все ДТО")
    public List<Auto> getDtoAll() {
        return autoServiceFeign.getAll();
    }

}
