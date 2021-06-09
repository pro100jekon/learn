package com.epam.smyrnov.module13.controller;

import com.epam.smyrnov.module13.model.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointController {

    @PostMapping("receive")
    public void receive(@RequestBody Log log) {
        System.out.println(log);
    }
}
