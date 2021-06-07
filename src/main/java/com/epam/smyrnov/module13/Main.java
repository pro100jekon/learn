package com.epam.smyrnov.module13;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequest;

public class Main {

    public static void main(String[] args) {
        SqsClient sqsClient = SqsClient.builder()
            .region(Region.US_WEST_2)
            .build();
        SendMessageBatchRequest request =
            SendMessageBatchRequest.builder()
                .queueUrl(sqsClient.getQueueUrl(GetQueueUrlRequest.builder().queueName("learnQueue").build()).queueUrl())
                .build();
        sqsClient.sendMessageBatch(request);
    }
}
