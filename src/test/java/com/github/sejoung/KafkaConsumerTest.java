package com.github.sejoung;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import scala.Predef;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class KafkaConsumerTest {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private KafkaConsumer consumer;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 10; i++) {
            producer.send("test" + i);
        }
    }

    @Test
    void receiveTest() throws InterruptedException {
        log.info(" count {} ", consumer.getLatch().getCount());
        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        log.info(" count {} ", consumer.getLatch().getCount());
        Assertions.assertThat(consumer.getPayload()).contains("test9");

    }
}