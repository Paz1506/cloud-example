package com.zaytsevp.configserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController(value = "/")
public class ConfigServerController {

    private final DiscoveryClient discoveryClient;

    @Autowired
    public ConfigServerController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @ResponseBody
    @RequestMapping(value = "/services")
    public String getEurekaServices() {
        List<String> services = discoveryClient.getServices();

        if (services == null && services.size() == 0) {
            return "No instances!";
        } else {
            return services.stream().collect(Collectors.joining(" / / "));
        }
    }
}
