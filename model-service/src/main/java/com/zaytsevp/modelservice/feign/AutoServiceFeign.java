package com.zaytsevp.modelservice.feign;

import com.zaytsevp.modelservice.model.Auto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

/**
 * Фейн для сервиса автомобилей
 *
 * @author Pavel Zaytsev
 */
//@FeignClient(value = "auto-service", configuration = {FeignConfig.class, OAuthInterceptorConfig.class})
@FeignClient(value = "auto-service")
public interface AutoServiceFeign {

    @GetMapping("/autos/all")
    List<Auto> getAll();

    @GetMapping("/autos/{id}")
    Auto getById(@PathVariable(name = "id") UUID id);

}
