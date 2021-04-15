package com.github.sejoung;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Component
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ListenableFuture<SendResult<String, String>> send(String payload) {
        log.info("sending payload='{}'", payload);
        return kafkaTemplate.send(Constants.TOPIC, payload);
    }
}

