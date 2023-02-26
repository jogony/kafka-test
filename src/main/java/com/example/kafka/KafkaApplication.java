package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.datatransfer.StringSelection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class KafkaApplication {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(KafkaApplication.class, args);
    }

}
