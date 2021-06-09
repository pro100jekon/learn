package com.epam.smyrnov.module13.sender;

import com.epam.smyrnov.module13.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class MessageSender {

    private final SqsClient client = SqsClient.builder().region(Region.US_EAST_2).build();
    private final ObjectMapper mapper = new ObjectMapper();

    public void sendMessage(Order order) throws JsonProcessingException {
        String json = mapper.writeValueAsString(order);
        SendMessageRequest request =
            SendMessageRequest.builder()
                .queueUrl(client.getQueueUrl(
                    GetQueueUrlRequest.builder().queueName("learnQueue").build()).queueUrl())
                .messageBody(json)
                .build();
        client.sendMessage(request);
    }
}
