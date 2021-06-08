package com.epam.smyrnov.module13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointController {

    @GetMapping
    public void receive(String content) {
        System.out.println(content);
    }
}
