package com.birchal.first_spring_app.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {
    
    public String getHelloWorld(String nome){
        return "Hello, " + nome + "!";
    }
}
