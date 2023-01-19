package com.thierry.marcelin.restfulservices.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// @RestController
@RequestMapping("/authenticate")
public class BasicAuth {

    @GetMapping
    public String basicAuthCheck() {
        return "Success";
    }

}
