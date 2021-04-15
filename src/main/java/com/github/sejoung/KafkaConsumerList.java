package com.github.sejoung;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class KafkaConsumerList {

    private CountDownLatch latch = new CountDownLatch(10);

    private String payload;

    @KafkaListener(topics = Constants.TOPIC, groupId = "list", properties = {"listener.type:batch"})
    public void receive(List<ConsumerRecord<?, ?>> records) {
        log.info("records size='{}'", records.size());
        records.forEach(this::runCountDown);
    }

    private void runCountDown(ConsumerRecord<?, ?> consumerRecord) {
        log.info("received payload='{}'", consumerRecord.toString());
        setPayload(consumerRecord.toString());
        latch.countDown();
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getPayload() {
        return payload;
    }
}
