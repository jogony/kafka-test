package com.example.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaProducerService kafkaProducerService;

    @RequestMapping("/publish")
    public String publish(String message) {
        kafkaProducerService.send(message);
        return "published a message : " + message;
    }
    @RequestMapping("/publish2")
    public String publishWithCallback(String message) {
        kafkaProducerService.sendWithCallback(message);
        return "published a message : " + message;
    }
    @RequestMapping("/publish3")
    public String publishJson(MyMessage message) {
        kafkaProducerService.sendJson(message);
        return "published a message : " + message.getName() + "," + message.getMessage();
    }
}
