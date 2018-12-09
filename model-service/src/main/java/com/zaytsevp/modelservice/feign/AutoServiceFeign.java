package com.zaytsevp.modelservice.feign;

import com.zaytsevp.modelservice.model.Auto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@FeignClient("auto-service")
public interface AutoServiceFeign {

    @RequestMapping("/all")
    List<Auto> getAll();

    @RequestMapping("/auto/{id}")
    Auto getById(@PathVariable(name = "id") UUID id);

}
