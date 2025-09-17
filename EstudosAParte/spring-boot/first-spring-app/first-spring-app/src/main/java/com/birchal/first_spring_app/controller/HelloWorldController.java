package com.birchal.first_spring_app.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.birchal.first_spring_app.service.HelloWorldService;

@RestController
// Controlador para a rota /hello-world
@RequestMapping("/hello-world")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    // Ira receber as requisições GET no /hello-world
    @GetMapping
    public String helloWorld(){
        return helloWorldService.getHelloWorld("Felipe");
    }

    @PostMapping("/{id}")
    public String helloWorldPost(@PathVariable("id") String id, @RequestBody User body){
        return "Hello " + body.getName() + " " + id;
    }
}
