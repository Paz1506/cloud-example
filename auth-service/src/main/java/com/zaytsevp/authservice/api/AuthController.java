package com.zaytsevp.authservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created on 11.02.2019.
 *
 * @author Pavel Zaytsev
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/user")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }
}
