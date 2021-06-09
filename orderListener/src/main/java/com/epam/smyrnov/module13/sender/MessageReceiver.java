package com.epam.smyrnov.module13.sender;

import com.epam.smyrnov.module13.model.Log;
import com.epam.smyrnov.module13.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class MessageReceiver {

    private final SqsClient client = SqsClient.builder().region(Region.US_EAST_2).build();
    private final ObjectMapper mapper = new ObjectMapper();

    public Order receiveOrder() throws JsonProcessingException {
        ReceiveMessageRequest request =
            ReceiveMessageRequest.builder()
                .queueUrl(client.getQueueUrl(
                    GetQueueUrlRequest.builder().queueName("learnQueue").build()).queueUrl())
                .build();
        ReceiveMessageResponse response = client.receiveMessage(request);
        Message message = response.messages().stream().findFirst().orElse(null);
        if (message == null) {
            return null;
        }
        String content = message.body();
        client.deleteMessage(DeleteMessageRequest.builder()
            .queueUrl(client.getQueueUrl(
                GetQueueUrlRequest.builder().queueName("learnQueue").build()).queueUrl())
            .receiptHandle(message.receiptHandle())
            .build());
        return mapper.readValue(content, Order.class);
    }

    public void logResult(Log log) throws JsonProcessingException {
        SnsClient client = SnsClient.builder()
            .region(Region.US_EAST_2)
            .build();
        PublishRequest request = PublishRequest.builder()
            .topicArn("arn:aws:sns:us-east-2:243434905698:learnTopic")
            .message(mapper.writeValueAsString(log))
            .build();
        client.publish(request);
    }
}
