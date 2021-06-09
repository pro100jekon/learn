package com.epam.smyrnov.module13.controller;

import com.epam.smyrnov.module13.model.Log;
import com.epam.smyrnov.module13.service.S3Service;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointController {

    @Autowired
    private S3Service service;

    @PostMapping("receive")
    public void receive(@RequestBody Log log) throws IOException {
        service.appendLogToS3File(log);
    }
}
