package com.com.twoteethreeeight.scheulingservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {
    private static Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSeatGenerateRequest(String scheduleId) {

        LOGGER.info(String.format("Message sent -> %s", scheduleId));

        Message<String> message = MessageBuilder
                .withPayload(scheduleId)
                .setHeader(KafkaHeaders.TOPIC, "schedule")
                .build();

        kafkaTemplate.send(message);
    }
}
