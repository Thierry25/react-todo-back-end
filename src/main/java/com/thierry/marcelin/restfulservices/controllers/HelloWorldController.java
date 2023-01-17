package com.thierry.marcelin.restfulservices.controllers;

import com.thierry.marcelin.restfulservices.models.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    @GetMapping
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello world");
    }

    @GetMapping("/path-variable/{name}")
    public HelloWorldBean helloWorldBeanWithName(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello %s", name));
    }
}
