package com.zaytsevp.autoservice.api;

import com.zaytsevp.autoservice.model.Auto;
import com.zaytsevp.autoservice.service.AutoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Pavel Zaytsev
 */
@RestController
@RequestMapping(value = "/autos")
@RequiredArgsConstructor
public class AutoController {

    private final AutoService autoService;

    @GetMapping(value = "/all")
    @ApiOperation("Получить все автомобили")
    public List<Auto> getAll() {
        return autoService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Получить автомобиль по идентификатору")
    public Auto getById(@PathVariable("id") UUID id) {
        return autoService.getById(id).orElse(Auto.builder().name("Not found")
                                                  .foundYear(0)
                                                  .build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/createRandom")
    @ApiOperation("Создать случайный автомобиль")
    public Auto createRandom() {
        return autoService.createRandom();
    }
}
