package com.example.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer1 {

    private final static String BOOTSTRAP_SERVER = "localhost:9092";
    private final static String TOPIC_NAME = "topic5";
    private final static String GROUP_ID = "group_one";
    public static void main(String args []) {
        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        //메시지를 처음부터 끝까지 읽기 설정
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);
        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

            for(ConsumerRecord<String, String> record : records) {
                System.out.println(">>>" + record);
            }
        }
    }
}
