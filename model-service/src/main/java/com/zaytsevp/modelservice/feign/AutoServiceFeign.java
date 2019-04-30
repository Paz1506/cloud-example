package com.zaytsevp.modelservice.feign;

import com.zaytsevp.modelservice.config.FeignConfig;
import com.zaytsevp.modelservice.config.OAuthInterceptorConfig;
import com.zaytsevp.modelservice.model.Auto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

/**
 * Фейн для сервиса автомобилей
 *
 * @author Pavel Zaytsev
 */
@FeignClient(value = "auto-service", configuration = {FeignConfig.class, OAuthInterceptorConfig.class})
public interface AutoServiceFeign {

    @RequestMapping("/autos/all")
    List<Auto> getAll();

    @RequestMapping("/autos/{id}")
    Auto getById(@PathVariable(name = "id") UUID id);

}
