package com.birchal.first_spring_app.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfiguration {
    public String helloMessage() {
        return "Hello, World!";
    }
}
