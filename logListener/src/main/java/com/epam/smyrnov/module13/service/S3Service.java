package com.epam.smyrnov.module13.service;

import com.epam.smyrnov.module13.model.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    @Autowired
    private S3Client s3Client;
    @Autowired
    private ObjectMapper objectMapper;

    public void appendLogToS3File(Log log) throws IOException {
        String content = objectMapper.readValue(
            s3Client.getObject(GetObjectRequest.builder().bucket("bucketjms").build()), String.class);
        content = content + System.lineSeparator() +  objectMapper.writeValueAsString(log);
        s3Client.putObject(
            PutObjectRequest.builder()
                .bucket("bucketjms")
                .acl("public-read")
                .build(),
            RequestBody.fromString(content));
    }
}
