package com.zaytsevp.autoservice.api;

import com.zaytsevp.autoservice.api.dto.AutoDto;
import com.zaytsevp.autoservice.api.mapper.AutoMapper;
import com.zaytsevp.autoservice.model.Auto;
import com.zaytsevp.autoservice.service.AutoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Pavel Zaytsev
 */
@RestController
@RequestMapping(value = "/autos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AutoController {

    private final AutoService autoService;

    private final AutoMapper autoMapper;

    @GetMapping(value = "/all")
    @ApiOperation("Получить все автомобили")
    public List<AutoDto> getAll() {
        return autoService.getAll()
                          .stream()
                          .map(autoMapper::toDto)
                          .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Получить автомобиль по идентификатору")
    public AutoDto getById(@PathVariable("id") UUID id) {
        return autoMapper.toDto(autoService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/createRandom")
    @ApiOperation("Создать случайный автомобиль")
    public AutoDto createRandom() {
        return autoMapper.toDto(autoService.createRandom());
    }
}
