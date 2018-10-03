package com.ibiapina.herokufirebase.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("helloworld")
public class HelloWorld {

    @GetMapping("/{name}/")
    private String sayHelloWith(@PathVariable("name") String name) {
        return "Hello World, " + name;
    }
}