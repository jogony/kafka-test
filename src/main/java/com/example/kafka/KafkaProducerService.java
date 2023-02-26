package com.example.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String TOPIC_NAME = "topic5";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, MyMessage> newKafkaTemplate;

    public void send(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }
    public void sendJson(MyMessage message) {
        newKafkaTemplate.send(TOPIC_NAME, message);
    }
    public void sendWithCallback(String message) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            return kafkaTemplate.send(TOPIC_NAME, message);
        }).thenAcceptAsync( s -> {
            try {
                SendResult<String, String> result = s.get();
                System.out.println("Sent " + message + " offset : " + result.getRecordMetadata());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Fail " + e.getMessage());
                throw new RuntimeException(e);
            }
        });
    }
}
