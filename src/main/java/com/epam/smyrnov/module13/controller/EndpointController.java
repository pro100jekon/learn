package com.epam.smyrnov.module13.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointController {

    @PostMapping("receive")
    public void receive(String content) {
        System.out.println(content);
    }
}
